package com.troyecto.marketplace.serviceimpls;

import com.troyecto.marketplace.dtos.user.UserRequest;
import com.troyecto.marketplace.dtos.user.UserResponse;
import com.troyecto.marketplace.entities.User;
import com.troyecto.marketplace.exceptions.ResourceNotFoundException;
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
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserResponse userResponse) {
        User user=userRepository.findById(userResponse.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userResponse.getId()));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user=userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user=userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());
        user.setPhone(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());
        User updatedUser=userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }

    @Override
    public String deleteUser(Long id) {
        User user=userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
        return "User deleted successfully with id: " + id;
    }
}