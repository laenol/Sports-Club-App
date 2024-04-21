package com.swegroup3.Sports.Club.App.Repositories;

import com.swegroup3.Sports.Club.App.Entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
