package com.swegroup3.Sports.Club.App.Controller;

import com.swegroup3.Sports.Club.App.Entities.Event;
import com.swegroup3.Sports.Club.App.Entities.Team;
import com.swegroup3.Sports.Club.App.Entities.User;
import com.swegroup3.Sports.Club.App.Services.EventService;
import com.swegroup3.Sports.Club.App.Services.MyUserDetails;
import com.swegroup3.Sports.Club.App.Services.TeamService;
import com.swegroup3.Sports.Club.App.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;




    @GetMapping("/{teamId}/new")
    public String showFormAddEvent(@PathVariable Long teamId,
                                   @AuthenticationPrincipal MyUserDetails userDetails,
                                   Model model){
        Optional<Team> team = teamService.findById(teamId);
        model.addAttribute("event", new Event());
        model.addAttribute("team", team.get());
        model.addAttribute("action", "/events/"+teamId+"/save");

        return "event/form_event";
    }
    @Autowired
    private TeamService teamService;

    @PostMapping("/{teamId}/save")
    public String saveEvent(
            @ModelAttribute Event event,
            @AuthenticationPrincipal MyUserDetails userDetails,
            @PathVariable Long teamId,
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateTimeStart,
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateTimeEnd) {

        Optional<Team> team = teamService.findById(teamId);
        String username = userDetails.getUsername();
        User userAuthenticated = userService.getByUsername(username);
        event.setUser(userAuthenticated);
        team.ifPresent(event::setTeam);
        event.setDateTimeStart(dateTimeStart);
        event.setDateTimeEnd(dateTimeEnd);
        eventService.SaveEvent(event);

        return "redirect:/teams/"+teamId;
    }

    @GetMapping("/team/{teamId}")
    public String showTeamEvents(@PathVariable Long teamId,
                                 @AuthenticationPrincipal MyUserDetails userDetails,
                                 Model model){
        String username = userDetails.getUsername();
        User user = userService.getByUsername(username);
        Optional<Team> teamOptional = teamService.findById(teamId);
        if(teamOptional.isPresent()){
            Team team = teamOptional.get();
            model.addAttribute("team", team);
            model.addAttribute("userLoggedID",user.getId());
            model.addAttribute("events", team.getEvents());
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

    @GetMapping("/edit/{id}")
    public String showFormModifyEvent(@PathVariable Long id,
                                       Model model){

        Optional<Event> eventOptional  = eventService.findById(id);
        if(eventOptional.isPresent()) {
            Event event= eventOptional.get();
            model.addAttribute("event",event );
            model.addAttribute("team", event.getTeam());
            model.addAttribute("action", "/events/"+event.getTeam().getId()+"/edit/" + id);

            return "event/form_event";
        }
        return "redirect:/events/";
    }

    @PostMapping("{teamId}/edit/{id}")
    public String modifyEvent(@PathVariable Long teamId,
                              @PathVariable Long id,
                              @AuthenticationPrincipal MyUserDetails userDetails,
                             @ModelAttribute Event event,
                              @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateTimeStart,
                              @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateTimeEnd) {
        String username = userDetails.getUsername();
        User user = userService.getByUsername(username);
        Optional<Event> eventOptional = eventService.findById(id);
        Optional<Team> teamOptional = teamService.findById(teamId);
        if(eventOptional.isPresent() && teamOptional.isPresent()) {
            event.setUser(user);
            event.setTeam(teamOptional.get());
            eventService.updateEvent(id, event);
        }
        return "redirect:/teams/";
    }
    @GetMapping("/{id}/delete")
    public String deleteEvent(@PathVariable Long id){
        Optional<Event> eventOptional = eventService.findById(id);
        if(eventOptional.isPresent()){
            System.out.println("HOLA");
            Event event = eventOptional.get();
            event.setUser(null);
            eventService.deleteEvent(event.getId());
        }
        return "redirect:/teams/list"; //
    }
}
