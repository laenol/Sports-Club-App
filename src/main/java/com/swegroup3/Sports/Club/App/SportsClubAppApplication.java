package com.swegroup3.Sports.Club.App;

import com.swegroup3.Sports.Club.App.Repositories.RoleRepository;
import com.swegroup3.Sports.Club.App.Repositories.UserRepository;
import com.swegroup3.Sports.Club.App.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SportsClubAppApplication {


	public static void main(String[] args) {
		SpringApplication.run(SportsClubAppApplication.class, args);
	}


//	Checking database implementation via CommandLine ("Testing ORM")

//	@Autowired
//	private UserService userService;
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private RoleRepository roleRepository;

//	@Override
//	public void run(String... args) throws Exception {
//		SecuredPassword encryptionPassword =  new SecuredPassword("password");
//		System.out.println(encryptionPassword.getEncodePassword());
//		//Adding user of different roles
//		userService.createUser(new User(1L, "Cristhian","cris21", encryptionPassword.getEncodePassword(), roleRepository.getReferenceById(1L)));
//		userService.createUser(new User(2L, "David","david", encryptionPassword.getEncodePassword(), roleRepository.getReferenceById(2L)));
//		userService.createUser(new User(3L, "Camille","camille", encryptionPassword.getEncodePassword(), roleRepository.getReferenceById(3L)));
//	}
}
