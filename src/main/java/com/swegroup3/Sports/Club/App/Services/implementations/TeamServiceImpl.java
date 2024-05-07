package com.swegroup3.Sports.Club.App.Services.implementations;

import com.swegroup3.Sports.Club.App.Entities.Team;
import com.swegroup3.Sports.Club.App.Entities.User;
import com.swegroup3.Sports.Club.App.Repositories.TeamRepository;
import com.swegroup3.Sports.Club.App.Repositories.UserRepository;
import com.swegroup3.Sports.Club.App.Repositories.EventRepository;
import com.swegroup3.Sports.Club.App.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

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
    public Team updateTeam(Long id, Team team) {
        Team teamDDBB = teamRepository.findById(id).orElse(null);
        if(teamDDBB != null){
            teamDDBB.setTeam_members(team.getTeam_members());
            teamDDBB.setLeader(teamDDBB.getLeader());
            teamDDBB.setName(team.getName());
            return teamRepository.save(team);
        }
        return null;
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public void addMember(Long teamId, User member) {
        Team team = teamRepository.findById(teamId).get();
        List<User> currentMembers = team.getTeam_members();
        currentMembers.add(member);
        team.setTeam_members(currentMembers);
        teamRepository.save(team);
    }

    @Override
    public long getTotalUserCountInTeams() {
        return teamRepository.findAll().stream()
                .mapToLong(team -> team.getTeam_members().size())
                .sum();
    }

    @Override
    public long getTotalEventCountInTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream()
                .mapToLong(team -> eventRepository.countByTeam(team))
                .sum();
    }
}
