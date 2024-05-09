package com.swegroup3.Sports.Club.App.Services;

import com.swegroup3.Sports.Club.App.Entities.Event;
import com.swegroup3.Sports.Club.App.Entities.Team;
import com.swegroup3.Sports.Club.App.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EventService {
    Event SaveEvent(Event event);
    Optional<Event> findById(Long id);
    List<Event> listAllEvents();
    Event updateEvent(Long id, Event event);

    Event addUserToEvent(Long id,Event event, User user);
    void deleteEvent(Long id);
    long getTotalCompletedEvents();
    long getTotalPendingEvents();

}