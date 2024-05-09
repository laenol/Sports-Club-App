package com.swegroup3.Sports.Club.App;

import com.swegroup3.Sports.Club.App.Entities.User;
import com.swegroup3.Sports.Club.App.Repositories.RoleRepository;
import com.swegroup3.Sports.Club.App.Repositories.UserRepository;
import com.swegroup3.Sports.Club.App.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SportsClubAppApplication {


	public static void main(String[] args) {
		SpringApplication.run(SportsClubAppApplication.class, args);
	}
}
