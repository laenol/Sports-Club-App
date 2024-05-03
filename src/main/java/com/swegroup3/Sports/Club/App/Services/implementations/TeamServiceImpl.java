package com.swegroup3.Sports.Club.App.Services.implementations;

import com.swegroup3.Sports.Club.App.Entities.Team;
import com.swegroup3.Sports.Club.App.Entities.User;
import com.swegroup3.Sports.Club.App.Repositories.TeamRepository;
import com.swegroup3.Sports.Club.App.Repositories.UserRepository;
import com.swegroup3.Sports.Club.App.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    public Optional<Team> findByName(String name) {

        return teamRepository.findByName(name);
    }

    @Override
    public List<Team> listAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team updateTeam(Team team) {

        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public void addMember(Long teamId, User member) {
        Team team = teamRepository.findById(teamId).get();
        List<User> currentMembers = team.getMembers();
        currentMembers.add(member);
        team.setMembers(currentMembers);
        teamRepository.save(team);
    }
}
