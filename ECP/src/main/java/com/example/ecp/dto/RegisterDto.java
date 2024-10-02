package com.example.ecp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDto {

    private String username;
    private String email;
    private String password;
}
