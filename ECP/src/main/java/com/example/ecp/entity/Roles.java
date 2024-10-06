package com.example.ecp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "roles")
@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String name;

    @Getter
    @Setter
    @JsonIgnore
    @ManyToMany(mappedBy = "rolesOfUsers",cascade = CascadeType.ALL)
    private Set<Users> usersHaveRoles = new HashSet<>();

}
