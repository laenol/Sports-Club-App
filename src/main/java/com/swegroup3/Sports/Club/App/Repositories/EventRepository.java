package com.swegroup3.Sports.Club.App.Repositories;

import com.swegroup3.Sports.Club.App.Entities.Event;
import com.swegroup3.Sports.Club.App.Entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    long countByTeam(Team team);
    long countByDateTimeEndBefore(Date dateTimeEnd);
    long countByDateTimeEndAfter(Date dateTimeEnd);
}
