package com.gama.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gama.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	@Query("{'title': {$regex: ?0, $options: 'i'}}")
	List<Post> pesquisarTitulo(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{$and: [{date: { $gte: ?1 }}, {date: { $lte: ?2 }} , { $or: [ {'title': {$regex: ?0, $options: 'i'}}, {'comentarios.text': {$regex: ?0, $options: 'i'}}, {'body': {$regex: ?0, $options: 'i'}} ] }]} ")
	List<Post> pesquisaCompleta(String text, Date minDt, Date maxDt);
	
}
