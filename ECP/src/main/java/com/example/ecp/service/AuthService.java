package com.example.ecp.service;

import com.example.ecp.dto.LoginDto;
import com.example.ecp.dto.RegisterDto;
import com.example.ecp.entity.Users;
import com.example.ecp.exception.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthService {
    public ResponseEntity<ResponseObject> register(RegisterDto registerDto);
    public ResponseEntity<ResponseObject> login(LoginDto loginDto);
    public ResponseEntity<ResponseObject> logout();
    public ResponseEntity<ResponseObject> changePassword(String username, String oldPassword, String newPassword);
    public List<Users> getAllUserInfo();
    public ResponseEntity<ResponseObject> deleteUserByName(String username);
}
