package com.swegroup3.Sports.Club.App.Controller;

import com.swegroup3.Sports.Club.App.Entities.Event;
import com.swegroup3.Sports.Club.App.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;



    @GetMapping("/new")
    public String showFormAddEvent(Model model){
        model.addAttribute("event", new Event());
        model.addAttribute("action", "/event/new");
        return "form";
    }

    @GetMapping("/event/{id}")
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
