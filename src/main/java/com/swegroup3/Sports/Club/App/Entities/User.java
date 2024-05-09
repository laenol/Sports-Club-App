package com.swegroup3.Sports.Club.App.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    public User(String name, String username, String encode, Role role) {
        this.name = name;
        this.username = username;
        this.password = encode;
        this.role = role;
    }

    @ManyToMany(mappedBy = "team_members")
    private List<Team> teams = new ArrayList<>();


    @ManyToMany(mappedBy = "event_members")
    private List<Event> events = new ArrayList<>();


}
