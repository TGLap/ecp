package com.example.ecp.service.Impl;

import com.example.ecp.dto.LoginDto;
import com.example.ecp.dto.RegisterDto;
import com.example.ecp.entity.Roles;
import com.example.ecp.entity.Users;
import com.example.ecp.exception.ResponseObject;
import com.example.ecp.repository.RoleRepository;
import com.example.ecp.repository.UserRepository;
import com.example.ecp.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl  implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ResponseObject> register(RegisterDto registerDto) {
        if(registerDto.getUsername()==null || userRepository.existsByUsername(registerDto.getUsername()) ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("bad_request","UserName have been used",registerDto.getUsername())
            );
        }
        if(registerDto.getPassword()==null || registerDto.getPassword().length()<6){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("bad_request","Password can't null or less than 6",registerDto.getPassword())
            );
        }
        if(registerDto.getEmail()==null || userRepository.existsByEmail(registerDto.getEmail()) ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("bad_request","Email have been used",registerDto.getEmail())
            );
        }
        Roles roles = roleRepository.getById(1);
        Users user = new Users();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.rolesOfUsers(roles);

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success","success",registerDto.getUsername()));
    }

    @Override
    public ResponseEntity<ResponseObject> login(LoginDto loginDto) {
        if(userRepository.existsByUsername(loginDto.getUsername())) {
            Optional<Users> users = userRepository.findByUsername(loginDto.getUsername());
            if (passwordEncoder.matches(loginDto.getPassword(), users.get().getPassword())) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "success", loginDto));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Bad_request", "wrong password", loginDto));

        }
        if(userRepository.existsByEmail(loginDto.getUsername())) {
            Optional<Users> users = userRepository.findByEmail(loginDto.getUsername());
            if (passwordEncoder.matches(loginDto.getPassword(), users.get().getPassword())) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "success", loginDto));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Bad_request", "wrong password", loginDto));

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Bad_request", "wrong username or email", loginDto));

    }

    @Override
    public ResponseEntity<ResponseObject> logout() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseObject> changePassword(String email, String oldPassword, String newPassword) {
        return null;
    }

    @Override
    public List<Users> getAllUserInfo() {
        List<Users> users = userRepository.findAll();
        return users;
    }

    @Override
    public ResponseEntity<ResponseObject> deleteUserByName(String username) {
        Optional<Users> user = userRepository.findByUsername(username);
        if(userRepository.existsByUsername(username)) {
          userRepository.deleteByUsername(username);
          return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Delete success", user));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Bad_request", "user not found", username));
    }

}
