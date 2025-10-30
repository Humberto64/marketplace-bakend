package com.troyecto.marketplace.serviceimpls;

import com.troyecto.marketplace.dtos.UserDTO;
import com.troyecto.marketplace.entities.User;
import com.troyecto.marketplace.exceptions.ResourceNotFoundException;
import com.troyecto.marketplace.mappers.OrderMapper;
import com.troyecto.marketplace.mappers.ReviewMapper;
import com.troyecto.marketplace.mappers.UserMapper;
import com.troyecto.marketplace.repositories.UserRepository;
import com.troyecto.marketplace.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar. Usuario no encontrado con id: " + id));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setAddress(userDetails.getAddress());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());

        // Limpieza de colecciones: romper referencias y vaciar listas para reemplazo total.
        user.getOrders().forEach(d -> d.setUser(null));
        user.getOrders().clear();
        user.getReviews().forEach(d -> d.setUser(null));
        user.getReviews().clear();

        // Comentario:
        // - Aquí se re-agregan orders/reviews desde DTO usando los mappers; se debe tener cuidado con IDs y con persistencia.
        if (userDetails.getOrders() != null) {
            userDetails.getOrders().forEach(orderDto ->
                    user.addOrder(OrderMapper.mapOrderDTOtoOrder(orderDto)));
        }
        if(userDetails.getReviews() != null) {
            userDetails.getReviews().forEach(reviewDto ->
                    user.addReview(ReviewMapper.mapReviewDTOtoReview(reviewDto)));
        }
        if(userDetails.getOrders() != null) {
            userDetails.getOrders().forEach(orderDto ->
                    user.addOrder(OrderMapper.mapOrderDTOtoOrder(orderDto)));
        }

        User updatedUser = userRepository.save(user);
        return UserMapper.toDTO(updatedUser);
    }

    @Override
    public String deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar. Usuario no encontrado con id: " + id));
        // Comentario:
        // - Al eliminar el usuario, por cascade = ALL las entidades relacionadas (orders/reviews/stores) se eliminarán o quedarán huérfanas según la configuración.
        userRepository.delete(user);
        return "Registro con ID " + id + " eliminado exitosamente.";
    }
}