package com.troyecto.marketplace.dtos;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Long phone;
    private String address;
    private String password;
    private String role;
    private List<OrderDTO> orders;
    private List<ReviewDTO> reviews;
    private List<StoreDTO> stores;
   }
