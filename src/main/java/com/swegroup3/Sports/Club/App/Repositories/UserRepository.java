package com.swegroup3.Sports.Club.App.Repositories;
import com.swegroup3.Sports.Club.App.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
