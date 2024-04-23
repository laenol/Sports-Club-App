package com.swegroup3.Sports.Club.App.Controller;

import com.swegroup3.Sports.Club.App.Entities.Team;
import com.swegroup3.Sports.Club.App.Repositories.TeamRepository;
import com.swegroup3.Sports.Club.App.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/new")
    public String showFormAddTeam(Model model){
        model.addAttribute("team", new Team());
        model.addAttribute("action", "/team/new");
        return "form";
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

}
