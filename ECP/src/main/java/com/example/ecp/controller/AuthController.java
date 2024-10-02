package com.example.ecp.controller;

import com.example.ecp.dto.RegisterDto;

import com.example.ecp.exception.ResponseObject;
import com.example.ecp.repository.RoleRepository;
import com.example.ecp.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register (@RequestBody RegisterDto registerDto) {
            return authService.register(registerDto);
    }
}
