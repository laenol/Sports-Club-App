package com.swegroup3.Sports.Club.App.Services;

import com.swegroup3.Sports.Club.App.Entities.Team;
import com.swegroup3.Sports.Club.App.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TeamService {
    Team saveTeam(Team team);
    Optional<Team> findById(Long id) ;
    Optional<Team> findByName(String name);
    List<Team> listAllTeams();
    Team updateTeam(Long id, Team team);
    void deleteTeam(Long id);
    void addMember(Long teamId, User member);
}
