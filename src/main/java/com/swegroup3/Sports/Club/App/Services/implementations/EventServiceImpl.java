package com.swegroup3.Sports.Club.App.Services.implementations;

import com.swegroup3.Sports.Club.App.Entities.Event;
import com.swegroup3.Sports.Club.App.Entities.Team;
import com.swegroup3.Sports.Club.App.Entities.User;
import com.swegroup3.Sports.Club.App.Repositories.EventRepository;
import com.swegroup3.Sports.Club.App.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event SaveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> listAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event updateEvent(Long id, Event event) {

        Event eventDDBB = eventRepository.findById(id).orElse(null);
        if (eventDDBB != null) {
            eventDDBB.setName(event.getName());
            eventDDBB.setTeam(event.getTeam());
            eventDDBB.setDescription(event.getDescription());
            eventDDBB.setDateTimeStart(event.getDateTimeStart());
            eventDDBB.setDateTimeEnd(event.getDateTimeEnd());
            eventDDBB.setMax_amount_participants(event.getMax_amount_participants());
            eventDDBB.setLocation(event.getLocation());

            eventDDBB.setUser(eventDDBB.getUser());
            eventRepository.save(eventDDBB);
        }
        return event;
    }

    @Override
    public Event addUserToEvent(Long id, User user) {
        Event eventDDBB = eventRepository.findById(id).orElse(null);
        if (eventDDBB != null) {
             List<User> tempUsers = eventDDBB.getEvent_members();
            System.out.println(tempUsers);
             tempUsers.add(user);
             eventDDBB.setEvent_members(tempUsers);
             eventRepository.save(eventDDBB);
        }
        return eventDDBB;
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public long getTotalCompletedEvents() {
        LocalDateTime now = LocalDateTime.now();
        return eventRepository.countByDateTimeEndBefore(now);
    }

    @Override
    public long getTotalPendingEvents() {
        LocalDateTime now = LocalDateTime.now();
        return eventRepository.countByDateTimeEndAfter(now);
    }
}
