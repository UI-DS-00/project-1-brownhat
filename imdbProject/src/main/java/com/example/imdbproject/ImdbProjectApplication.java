package com.example.imdbproject;

import com.example.imdbproject.model.Role;
import com.example.imdbproject.service.UserServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ImdbProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImdbProjectApplication.class, args);
	}


	@Bean
	// we are using default spring password encoder instead of making them ourselves
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Bean
	CommandLineRunner run(UserServiceImp userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_USER"));

		};
}

}
