package com.example.ecp.service.Impl;

import com.example.ecp.dto.RegisterDto;
import com.example.ecp.entity.Roles;
import com.example.ecp.entity.Users;
import com.example.ecp.exception.ResponseObject;
import com.example.ecp.repository.RoleRepository;
import com.example.ecp.repository.UserRepository;
import com.example.ecp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl  implements AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
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
        Set<Roles> rolesSet = null;
        Users user = new Users();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Roles roles = roleRepository.findByName("USER").get();
        rolesSet = user.getRoles();
        rolesSet.add(roles);
        user.setRoles(rolesSet);

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success","success",user));
    }
}
