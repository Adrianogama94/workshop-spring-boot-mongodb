package com.gama.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gama.workshopmongo.domain.User;
import com.gama.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		
		User adriano = new User(null, "Adriano Gama", "adriano@gmail.com");
		User sandra = new User(null, "Sandra Pires", "sandra@gmail.com");
		User aline = new User(null, "Aline Dias", "aline@gmail.com");
		
		userRepository.saveAll(Arrays.asList(adriano, sandra, aline));
		
	}

}
