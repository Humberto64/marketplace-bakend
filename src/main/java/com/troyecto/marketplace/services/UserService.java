package com.troyecto.marketplace.services;

import com.troyecto.marketplace.dtos.UserDTO;
import java.util.List;


public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long id, UserDTO userDetails);

    String deleteUser(Long id);
}