package com.swegroup3.Sports.Club.App.dto;

import com.swegroup3.Sports.Club.App.Entities.Role;
import lombok.Data;

@Data
public class UserDto{
    private String name;
    private String username;
    private String password;
    private Role role;

    public UserDto(String name, String username, String password, Role role) {
        super();
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

