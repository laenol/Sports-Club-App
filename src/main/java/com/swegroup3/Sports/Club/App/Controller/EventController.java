package com.swegroup3.Sports.Club.App.Controller;

import com.swegroup3.Sports.Club.App.Entities.Comment;
import com.swegroup3.Sports.Club.App.Entities.Event;
import com.swegroup3.Sports.Club.App.Entities.Team;
import com.swegroup3.Sports.Club.App.Entities.User;
import com.swegroup3.Sports.Club.App.Services.CommentService;
import com.swegroup3.Sports.Club.App.Services.EventService;
import com.swegroup3.Sports.Club.App.Services.TeamService;
import com.swegroup3.Sports.Club.App.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
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
    @Autowired
    private CommentService commentService;

    @GetMapping("/{teamId}/new")
    public String showFormAddEvent(@PathVariable Long teamId, @AuthenticationPrincipal MyUserDetails userDetails, Model model) {
        Optional<Team> team = teamService.findById(teamId);
        model.addAttribute("event", new Event());
        model.addAttribute("team", team.orElse(null));
        return "event/form_event";
    }

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
        eventService.saveEvent(event); // Make sure it's `saveEvent`, not `SaveEvent` if using standard naming conventions
        return "redirect:/teams/" + teamId;
    }

    @GetMapping("/team/{teamId}")
    public String showTeamEvents(@PathVariable Long teamId, Model model) {
        Optional<Team> teamOptional = teamService.findById(teamId);
        teamOptional.ifPresent(team -> {
            model.addAttribute("team", team);
            model.addAttribute("events", team.getEvents());
        });
        return "event/show_team_events";
    }

    @GetMapping("/{id}")
    public String showEvent(@PathVariable Long id, Model model) {
        Optional<Event> eventOptional = eventService.findById(id);
        if(eventOptional.isPresent()){
            Event event = eventOptional.get();
            model.addAttribute("event", event);
            model.addAttribute("team", event.getTeam());
            List<Comment> comments = commentService.findCommentsByEventId(id); // Ensure such a method exists or implement it
            model.addAttribute("comments", comments);
            model.addAttribute("newComment", new Comment());
        } else {
            model.addAttribute("newComment", new Comment()); // Still add this even if the event isn't found
            return "redirect:/error-page"; // Redirect to an error page or similar
        }
        return "event/show_event";
    }

    @PostMapping("/{id}/add-comment")
    public String addCommentToEvent(@ModelAttribute Comment comment, @PathVariable Long id) {
        Optional<Event> eventOptional = eventService.findById(id);
        eventOptional.ifPresent(event -> {
            comment.setEvent(event);
            commentService.createComment(comment);
        });
        return "redirect:/events/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }
}
