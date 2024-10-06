package com.example.ecp;

import com.example.ecp.entity.Roles;
import com.example.ecp.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class EcpApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EcpApplication.class, args);
    }
    @Autowired
    RoleRepository repository;
    @Override
    public void run(String... args) throws Exception {
//        Roles roles = new Roles();
//        roles.setName("USER");
//        Roles roles1 = new Roles();
//        roles1.setName("ADMIN");
//        repository.save(roles);
//        repository.save(roles1);

    }
}
