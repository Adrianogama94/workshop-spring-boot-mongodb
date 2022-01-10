package com.gama.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gama.workshopmongo.domain.Post;
import com.gama.workshopmongo.resources.util.URL;
import com.gama.workshopmongo.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post  obj = service.findById(id);
		
		
		return ResponseEntity.ok().body(obj);

	}
	@GetMapping("/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
		text = URL.decodePara(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);

	}
	
	@GetMapping("/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDt", defaultValue = "") String minDt,
			@RequestParam(value = "maxDt", defaultValue = "") String maxDt){
		text = URL.decodePara(text);
		Date min = URL.converteDate(minDt, new Date(0L));
		Date max = URL.converteDate(maxDt, new Date());
	
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);

	}
	
}
