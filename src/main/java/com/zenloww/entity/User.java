package com.zenloww.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
// @AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;

    private String username;
    private String email;
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Task> task = new ArrayList<>();

    public User(String username, String email, String role) {
        this.username=username;
        this.email=email;
        this.role=role;
    }
}
