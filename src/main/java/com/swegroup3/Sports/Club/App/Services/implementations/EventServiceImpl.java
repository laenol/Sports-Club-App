package com.swegroup3.Sports.Club.App.Services.implementations;

import com.swegroup3.Sports.Club.App.Entities.Event;
import com.swegroup3.Sports.Club.App.Entities.Team;
import com.swegroup3.Sports.Club.App.Entities.User;
import com.swegroup3.Sports.Club.App.Repositories.EventRepository;
import com.swegroup3.Sports.Club.App.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            // eventDDBB.setUsers(event.getUsers());
            eventDDBB.setMax_amount_participants(event.getMax_amount_participants());
            eventDDBB.setLocation(event.getLocation());
        }
        return event;
    }

    @Override
    public Event addUserToEvent(Long id, Event event, User user) {
        Event eventDDBB = eventRepository.findById(id).orElse(null);
        if (eventDDBB != null) {
            // Set<User> tempUsers = event.getUsers();
            // tempUsers.add(user);
            // eventDDBB.setUsers(tempUsers);
        }
        return event;
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public long getTotalCompletedEvents() {
        return eventRepository.countByDateTimeEndBefore(new Date());
    }

    @Override
    public long getTotalPendingEvents() {
        return eventRepository.countByDateTimeEndAfter(new Date());
    }
}
