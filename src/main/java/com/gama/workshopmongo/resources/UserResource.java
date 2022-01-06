package com.gama.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gama.workshopmongo.domain.User;

@RestController
@RequestMapping(value ="/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>>findAll(){
		User adriano =  new User("1", "Adriano Gama", "adr@gmail.com");
		User sandra = new User("2", "Sandra Pires", "sandra@gmail.com");
		List<User> users = new ArrayList<>();
		users.addAll(Arrays.asList(adriano, sandra));
		
		
		return ResponseEntity.ok().body(users);
		
	}
	
	
}
