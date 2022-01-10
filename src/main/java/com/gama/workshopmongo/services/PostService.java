package com.gama.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.workshopmongo.domain.Post;
import com.gama.workshopmongo.repository.PostRepository;
import com.gama.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		return repo.pesquisarTitulo(text);
	}
	
	public List<Post> fullSearch(String text, Date minDt, Date maxDt){
		maxDt =  new Date(maxDt.getTime() + 24 * 60 * 60 * 1000);
		return repo.pesquisaCompleta(text, minDt, maxDt) ;
	}
	
}
