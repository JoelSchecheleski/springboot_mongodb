package com.uppertools.workshop.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.uppertools.workshop.domain.User;
import com.uppertools.workshop.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		User u1 = new User(null, "Joel Schecheleski", "joel.jsp@gmail.com");
		User u2 = new User(null, "Kátia Michele Jurk Avalos Schecheleski", "kmjavalos@gmail.com");
		User u3 = new User(null, "Miguel Schecheleski", "miguel@gmail.com");
		User u4 = new User(null, "Raquel Schecheleski", "raquel@gmail.com");
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));

	}

}
