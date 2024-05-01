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


//	Checking database implementation via CommandLine ("Testing ORM")

//	@Autowired
//	private UserService userService;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@Autowired
//	private RoleRepository roleRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		String password = passwordEncoder.encode("password");
//		//Adding user of different roles
//		userService.createUser(new User(1L,"Cristhian","cris21",password, roleRepository.getReferenceById(1L)));
//		userService.createUser(new User(2L,"David","david", password, roleRepository.getReferenceById(2L)));
//		userService.createUser(new User(3L, "Camille","camille", password, roleRepository.getReferenceById(3L)));
//	}
}
