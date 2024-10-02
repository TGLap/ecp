package com.example.ecp.service;

import com.example.ecp.dto.RegisterDto;
import com.example.ecp.exception.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<ResponseObject> register(RegisterDto registerDto);
}
