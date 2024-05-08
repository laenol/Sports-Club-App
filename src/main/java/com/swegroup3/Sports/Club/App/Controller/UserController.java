package com.swegroup3.Sports.Club.App.Controller;

import com.swegroup3.Sports.Club.App.Entities.Role;
import com.swegroup3.Sports.Club.App.Repositories.RoleRepository;
import com.swegroup3.Sports.Club.App.Services.UserService;
import com.swegroup3.Sports.Club.App.Services.TeamService;
import com.swegroup3.Sports.Club.App.Services.EventService;
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
    private TeamService teamService;

    @Autowired
    private EventService eventService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") UserDto userDto, Model model) {
        model.addAttribute("roles", roleRepository.findAll()); // Add this line
        return "register";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
        Long roleId = userDto.getRole().getId();
        System.out.println(roleId);
        Role role = roleRepository.getReferenceById(roleId.longValue());
        userDto.setRole(role);
        userService.saveUser(userDto);
        model.addAttribute("message", "Registered Successfully!");
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

        long totalCountTeams = teamService.getTotalTeamCount();
        model.addAttribute("totalCountTeams", totalCountTeams);
        long totalUserCountInTeams = teamService.getTotalUserCountInTeams();
        model.addAttribute("totalUserCountInTeams", totalUserCountInTeams);
        long totalEventCountInTeams = teamService.getTotalEventCountInTeams();
        model.addAttribute("totalEventCountInTeams", totalEventCountInTeams);
        long totalCompletedEvents = eventService.getTotalCompletedEvents();
        model.addAttribute("totalCompletedEvents", totalCompletedEvents);

        long totalPendingEvents = eventService.getTotalPendingEvents();
        model.addAttribute("totalPendingEvents", totalPendingEvents);
        return "index";
    }

    @GetMapping("/admin-page")
    public String adminPage (Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "admin";
    }

}
