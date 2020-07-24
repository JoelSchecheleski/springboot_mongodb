package com.uppertools.workshop.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.uppertools.workshop.domain.Post;
import com.uppertools.workshop.repository.PostRepository;
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
	 *
	 * @param text Texto usado para localizar um registro
	 * @return Reetorna uma lista de posts se localizar
	 */
	public List<Post> findByTitle(String text) {
		return this.repository.findByTitleContainingIgnoreCase(text);
	}

	/**
	 * Busca avançada através de um texto contigo no objeto por regex
	 *
	 * @param text Texto usado para localizar um registro
	 * @return Reetorna uma lista de posts se localizar
	 */
	public List<Post> searchTitle(String text) {
		return this.repository.searchTitle(text);
	}

	/**
	 * Localiza um objeto pelo texto dentro de uma faixa de dias
	 *
	 * @param text    Texto da pesquisa
	 * @param minDate Data mínima de pesquisa
	 * @param maxDate Data máxima de pesquisa
	 * @return Retorna uma lista de posts dentro da faixa de tempo especificado
	 */
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return this.repository.fullSearch(text, minDate, maxDate);
	}

}
