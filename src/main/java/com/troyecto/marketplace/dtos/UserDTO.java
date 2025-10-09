package com.troyecto.marketplace.dtos;

import lombok.*;

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
   }//hola