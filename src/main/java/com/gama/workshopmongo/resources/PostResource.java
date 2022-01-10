package com.gama.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gama.workshopmongo.domain.Post;
import com.gama.workshopmongo.domain.User;
import com.gama.workshopmongo.dto.UserDTO;
import com.gama.workshopmongo.services.UserService;

@RestController
@RequestMapping("/users")
public class PostResource {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>>findAll(){
		List<User> users = service.findAll();
		List<UserDTO>usersDTO = users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(usersDTO);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable String id){
		User  obj = service.findById(id);
		
		
		return ResponseEntity.ok().body(obj);

	}
	
	@GetMapping("/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		User  obj = service.findById(id);
				
		return ResponseEntity.ok().body(obj.getPosts());

	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO){
		User  obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		
		
		return ResponseEntity.noContent().build();

	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id){
		User  obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();

	}
}