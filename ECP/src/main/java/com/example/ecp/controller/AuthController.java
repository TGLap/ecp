package com.example.ecp.controller;

import com.example.ecp.dto.LoginDto;
import com.example.ecp.dto.RegisterDto;

import com.example.ecp.entity.Roles;
import com.example.ecp.entity.Users;
import com.example.ecp.exception.ResponseObject;
import com.example.ecp.repository.RoleRepository;
import com.example.ecp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;
    private RoleRepository roleRepository;

    @GetMapping("/getUser")
    public ResponseEntity<List<Users>> getUser() {
        List<Users> users = authService.getUserInfo();
            return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register (@RequestBody RegisterDto registerDto) {
            return authService.register(registerDto);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login (@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }
}
