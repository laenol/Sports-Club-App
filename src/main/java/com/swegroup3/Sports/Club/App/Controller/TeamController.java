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

import java.util.ArrayList;
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
    private String listTeams(@AuthenticationPrincipal MyUserDetails userDetails, Model model){
        String username = userDetails.getUsername();
        User userAuthenticated = userService.getByUsername(username);
        model.addAttribute("teams", teamService.listAllTeams());
        model.addAttribute("user", userAuthenticated);
        return "team/list_teams";

    }

    @GetMapping("/new")
    public String showFormAddTeam(@AuthenticationPrincipal MyUserDetails userDetails, Model model){
        String username = userDetails.getUsername();
        User userAuthenticated = userService.getByUsername(username);
        model.addAttribute("team", new Team());
        model.addAttribute("leaderId", userAuthenticated.getId());
        model.addAttribute("members", userService.userRolesList(3L));
         return "team/form_team";
    }
    @PostMapping("/new")
    public String saveTeam(@ModelAttribute Team team,
                           @RequestParam(value = "leaderId") Long leaderId,
                           @RequestParam(value = "membersId", required = false) List<Long> membersId){
        Optional<User> leader =  userService.obtainById(leaderId);
        leader.ifPresent(team::setLeader);
        if(membersId == null){
            team.setMembers(new ArrayList<>());
        }else{
            List<User> members = userService.findByIds(membersId);
            team.setMembers(members);
        }

        teamService.saveTeam(team);
        return "redirect:/teams/list";
    }

    @GetMapping("/{id}")
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
    @GetMapping("/{teamId}/add")
    public String addMemberToTeam(@AuthenticationPrincipal MyUserDetails userDetails, @PathVariable Long teamId){
        String username = userDetails.getUsername();
        User user = userService.getByUsername(username);
        teamService.addMember(teamId, user);
        return "redirect:/teams/";
    }
    @GetMapping("{teamId}/leave")
    public String leaveMemberFromTeam(@AuthenticationPrincipal MyUserDetails userDetails, @PathVariable Long teamId){
        String username = userDetails.getUsername();
        User user = userService.getByUsername(username);
        Optional<Team> teamMemberList = teamService.findById(teamId);
        if(teamMemberList.isPresent()){
            Team teamMember = teamMemberList.get();
            Boolean isMember = teamMember.getMembers().contains(user);
            if(isMember){
                teamMember.getMembers().remove(user);
                teamService.saveTeam(teamMember);
            }
        }
        return "redirect:/teams/";
    }

    @GetMapping("/{teamId}/delete")
    public String deleteTeam(@AuthenticationPrincipal MyUserDetails userDetails ,@PathVariable Long teamId){
        String username = userDetails.getUsername();
        User user = userService.getByUsername(username);
        Optional<Team> teamOptional = teamService.findById(teamId);
        if(teamOptional.isPresent()){
            Team team = teamOptional.get();
            team.setLeader(null);
            teamService.deleteTeam(teamId);
        }
        return "redirect:/teams/";
    }
}
