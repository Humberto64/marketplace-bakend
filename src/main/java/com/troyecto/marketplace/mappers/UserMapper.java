package com.troyecto.marketplace.mappers;

import com.troyecto.marketplace.dtos.OrderDTO;
import com.troyecto.marketplace.dtos.UserDTO;
import com.troyecto.marketplace.entities.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserMapper {


    public static UserDTO toDTO(User user) {
        if  (user == null) return null;

        List<OrderDTO> orderDTOs = null;
        if(user.getOrders() != null) {
            orderDTOs = user.getOrders()
                    .stream()
                    .map(OrderMapper::mapOrderToOrderDTO)
                    .collect(Collectors.toList());
        }

        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setPassword(user.getPassword());
        dto.setAddress(user.getAddress());
        dto.setRole(user.getRole());
        dto.setOrders(orderDTOs);
        return dto;
    }


    public static User toEntity(UserDTO dto) {
        if(dto == null) return null;

        User user = new User();

        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        user.setAddress(dto.getAddress());
        user.setRole(dto.getRole());

        if(dto.getOrders() != null){
            dto.getOrders().stream()
                    .filter(Objects::nonNull)
                    .map(OrderMapper::mapOrderDTOtoOrder)
                    .forEach(user::addOrder);
        }

        return user;
    }
}