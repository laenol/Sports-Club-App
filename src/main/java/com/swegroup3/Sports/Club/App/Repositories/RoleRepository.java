package com.swegroup3.Sports.Club.App.Repositories;

import com.swegroup3.Sports.Club.App.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String teamLeader);
}
