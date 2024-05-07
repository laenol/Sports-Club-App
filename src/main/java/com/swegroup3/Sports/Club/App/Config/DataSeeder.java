package com.swegroup3.Sports.Club.App.Config;
import com.swegroup3.Sports.Club.App.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.swegroup3.Sports.Club.App.Entities.Role;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Autowired
    public DataSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
            Role teamLeaderRole = roleRepository.findByName("LEADER");
            if (teamLeaderRole == null) {
                teamLeaderRole = new Role();
                teamLeaderRole.setName("LEADER");
                roleRepository.save(teamLeaderRole);
            }

            Role memberRole = roleRepository.findByName("MEMBER");
            if (memberRole == null) {
                memberRole = new Role();
                memberRole.setName("MEMBER");
                roleRepository.save(memberRole);
        }
    }
}
