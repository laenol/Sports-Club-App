package com.swegroup3.Sports.Club.App.Controller;

import com.swegroup3.Sports.Club.App.Entities.Team;
import com.swegroup3.Sports.Club.App.Entities.User;
import com.swegroup3.Sports.Club.App.Services.MyUserDetails;
import com.swegroup3.Sports.Club.App.Services.TeamService;
import com.swegroup3.Sports.Club.App.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;

    @GetMapping({"/","/list"})
    private String listTeams(Model model){
        model.addAttribute("teams", teamService.listAllTeams());
        return "team/list_teams";

    }

    @GetMapping("/new")
    public String showFormAddTeam(@AuthenticationPrincipal MyUserDetails userDetails, Model model){
        String username = userDetails.getUsername();
        User userAuthenticated = userService.getByUsername(username);
        model.addAttribute("team", new Team());
        model.addAttribute("userAuthenticated", userAuthenticated);
        model.addAttribute("users", userService.obtainAll());
        model.addAttribute("action", "/team/new");
        return "team/form_team";
    }

    @GetMapping("/team/{id}")
    public String showTeam(@PathVariable Long id, Model model){
        Optional<Team> teamOptional = teamService.findById(id);
        if(teamOptional.isPresent()){
            Team team = teamOptional.get();
            model.addAttribute("team", team);
            model.addAttribute("leader", team.getLeader());
            model.addAttribute("members", team.getMembers());
            //Todo Events / comments
        }
        return "team/show_team";
    }

    @PostMapping("/save")
    public String saveTeam(@ModelAttribute Team team,
                           @RequestParam(value = "leaderId") Long leaderId,
                           @RequestParam(value = "membersId") List<Long> membersId){
        Optional<User> leader =  userService.obtainById(leaderId);
        leader.ifPresent(team::setLeader);

        List<User> members = userService.findByIds(membersId);
        team.setMembers(members);

        teamService.saveTeam(team);
        return "redirect:/teams/list";
    }

    @GetMapping("/{id}/members")
    public String showTeamMembers(@PathVariable Long id, Model model){
        Optional<Team> teamOptional = teamService.findById(id);
        if(teamOptional.isPresent()){
            Team team = teamOptional.get();
            model.addAttribute("team", team);
            model.addAttribute("members", team.getMembers());
        }
        return "team/show_team_members";
    }
}
