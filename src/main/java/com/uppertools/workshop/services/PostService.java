package com.uppertools.workshop.services;

import java.util.List;
import java.util.Optional;

import com.uppertools.workshop.domain.Post;
import com.uppertools.workshop.domain.User;
import com.uppertools.workshop.dto.UserDTO;
import com.uppertools.workshop.repository.PostRepository;
import com.uppertools.workshop.repository.UserRepository;
import com.uppertools.workshop.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	/**
	 * Busca um post por ID
	 *
	 * @param id Código do post a ser localizado
	 * @return Objeto Post localizado
	 */
	public Post findById(String id) {
		Optional<Post> obj = this.repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	/**
	 * Busca avançada através de um texto contigo no objeto
	 * @param text Texto usado para localizar um registro
	 * @return Reetorna uma lista de posts se localizar
	 */
	public List<Post> findByTitle(String text) {
		return this.repository.findByTitleContainingIgnoreCase(text);
	}

}
