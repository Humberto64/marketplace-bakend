package com.troyecto.marketplace.serviceimpls;

import com.troyecto.marketplace.dtos.UserDTO;
import com.troyecto.marketplace.entities.User;
import com.troyecto.marketplace.exceptions.ResourceNotFoundException;
import com.troyecto.marketplace.mappers.OrderMapper;
import com.troyecto.marketplace.mappers.UserMapper;
import com.troyecto.marketplace.repositories.OrderRepository;
import com.troyecto.marketplace.repositories.UserRepository;
import com.troyecto.marketplace.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
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

        user.getOrders().forEach(d -> d.setUser(null));
        user.getOrders().clear();

        if (userDetails.getOrders() != null) {
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
        userRepository.delete(user);
        return "Registro con ID " + id + " eliminado exitosamente.";
    }
}