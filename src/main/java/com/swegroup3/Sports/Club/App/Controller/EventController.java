package com.swegroup3.Sports.Club.App.Controller;

import com.swegroup3.Sports.Club.App.Entities.Event;
import com.swegroup3.Sports.Club.App.Entities.Team;
import com.swegroup3.Sports.Club.App.Entities.User;
import com.swegroup3.Sports.Club.App.Services.EventService;
import com.swegroup3.Sports.Club.App.Services.MyUserDetails;
import com.swegroup3.Sports.Club.App.Services.TeamService;
import com.swegroup3.Sports.Club.App.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;




    @GetMapping("/{teamId}/new")
    public String showFormAddEvent(@PathVariable Long teamId,
                                    @AuthenticationPrincipal MyUserDetails userDetails,
                                   Model model){
        String username = userDetails.getUsername();
        User userAuthenticated = userService.getByUsername(username);
        Optional<Team> team = teamService.findById(teamId);
        model.addAttribute("event", new Event());
        model.addAttribute("team", team.get());
        return "event/form_event";
    }
    @PostMapping("/{teamId}/save")
    public String saveEvent(@ModelAttribute Event event,
                            @PathVariable Long teamId){
        Optional<Team> team = teamService.findById(teamId);
        team.ifPresent(event::setTeam);
        eventService.SaveEvent(event);

        return "redirect:/teams/"+teamId;
    }

    @GetMapping("/team/{teamId}")
    public String showTeamEvents(@PathVariable Long teamId, Model model){
        Optional<Team> teamOptional = teamService.findById(teamId);
        if(teamOptional.isPresent()){
            Team team = teamOptional.get();
            model.addAttribute("team", team);
          //  model.addAttribute("events", team.getEvents());
        }
        return "event/show_team_events";
    }

    @GetMapping("/{id}")
    public String showEvent(@PathVariable Long id, Model model){
        Optional<Event> eventOptional = eventService.findById(id);
        if(eventOptional.isPresent()){
            Event event = eventOptional.get();
            model.addAttribute("event", event);
            model.addAttribute("team", event.getTeam());
        }
        return "event/show_event";
    }


    @GetMapping("/{id}/delete")
    public String deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return "redirect:"; //
    }
}
