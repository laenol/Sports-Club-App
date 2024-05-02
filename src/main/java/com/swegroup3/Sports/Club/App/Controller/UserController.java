package com.swegroup3.Sports.Club.App.Controller;

import com.swegroup3.Sports.Club.App.Repositories.RoleRepository;
import com.swegroup3.Sports.Club.App.Services.UserService;
import com.swegroup3.Sports.Club.App.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;


@Controller
public class UserController {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
        return "register";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
        UserDto user = userDto;
        user.setRole(roleRepository.getReferenceById(3L));
        userService.saveUser(userDto);
        model.addAttribute("message", "Registered Successfuly!");
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user-page")
    public String userPage (Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "index";
    }

    @GetMapping("/admin-page")
    public String adminPage (Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "admin";
    }

}
