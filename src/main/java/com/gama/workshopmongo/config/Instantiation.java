package com.gama.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gama.workshopmongo.domain.Post;
import com.gama.workshopmongo.domain.User;
import com.gama.workshopmongo.dto.AuthorDTO;
import com.gama.workshopmongo.dto.CommentDTO;
import com.gama.workshopmongo.repository.PostRepository;
import com.gama.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User adriano = new User(null, "Adriano Gama", "adriano@gmail.com");
		User sandra = new User(null, "Sandra Pires", "sandra@gmail.com");
		User aline = new User(null, "Aline Dias", "aline@gmail.com");
		
		userRepository.saveAll(Arrays.asList(adriano, sandra, aline));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Viajando para são paulo", new AuthorDTO(adriano));
		Post post2 = new Post(null, sdf.parse("01/07/2021"), "Mais uma nova conquista!", "Finalmente consegui comprar a minha casa propría.", new AuthorDTO(adriano));
		
		CommentDTO c1 = new CommentDTO("Boa viagem Filho!!", sdf.parse("21/03/2018"), new AuthorDTO(sandra));
		CommentDTO c2 = new CommentDTO("Aproveite bem", sdf.parse("21/03/2018"), new AuthorDTO(aline));
		CommentDTO c3 = new CommentDTO("Parabêns pela conquista!!", sdf.parse("01/07/2021"), new AuthorDTO(aline));

		post1.getComentarios().addAll(Arrays.asList(c1, c2));
		post2.getComentarios().addAll(Arrays.asList(c3));
		
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		adriano.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(adriano);
		
		
	}

}
