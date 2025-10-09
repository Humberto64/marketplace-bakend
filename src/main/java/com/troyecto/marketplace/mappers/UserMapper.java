package com.troyecto.marketplace.mappers;

import com.troyecto.marketplace.dtos.UserDTO;
import com.troyecto.marketplace.entities.User;

public class UserMapper {


    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setPassword(user.getPassword());
        dto.setAddress(user.getAddress());
        dto.setRole(user.getRole());
        return dto;
    }


    public static User toEntity(UserDTO dto) {
        User user = new User();

        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        user.setAddress(dto.getAddress());
        user.setRole(dto.getRole());
        return user;
    }
}