package com.uppertools.workshop.resources;

import java.util.List;

import com.uppertools.workshop.domain.Post;
import com.uppertools.workshop.resources.util.URL;
import com.uppertools.workshop.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	/**
	 * Busca todos os posts
	 *
	 * @return Retorna um array contendo todos os usuários cadastrados
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable("id") String id) {
		Post post = this.service.findById(id);
		return ResponseEntity.ok().body(post);
	}

	/**
	 * Realiza a busca por um texto contido no objeto
	 *
	 * @param text Texto usado como argumento de pesquisa
	 * @return Retorna um conjunto de posts ou vazio
	 */
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		text = URL.decodeParam(text);
		List<Post> list = this.service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}

}
