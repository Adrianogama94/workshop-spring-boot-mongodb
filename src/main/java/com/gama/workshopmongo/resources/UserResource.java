package com.gama.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gama.workshopmongo.domain.Post;
import com.gama.workshopmongo.services.PostService;

@RestController
@RequestMapping("/posts")
public class UserResource {

	@Autowired
	private PostService service;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post  obj = service.findById(id);
		
		
		return ResponseEntity.ok().body(obj);

	}
	
}
