package com.uppertools.workshop.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.uppertools.workshop.domain.Post;
import com.uppertools.workshop.domain.User;
import com.uppertools.workshop.dto.UserDTO;
import com.uppertools.workshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	/**
	 * Busca todos os usuários no contexto GET
	 *
	 * @return Retorna um array contendo todos os usuários cadastrados
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = this.service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}

	/**
	 * Busca um usuário pelo ID
	 *
	 * @param id Código do usuário a ser localizado
	 * @return Retorna um Usuário caso consiga localizar
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getById(@PathVariable String id) {
		User obj = this.service.findById(id);
		return ResponseEntity.ok(new UserDTO(obj));
	}

	/**
	 * Cria um novo objeto
	 *
	 * @param obj Objeto recebido na requisição
	 * @return Retorna o objeto inserido
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User obj = this.service.fromDTO(objDto);
		obj = this.service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/**
	 * Deleta um usuário pelo ID
	 *
	 * @param id Código do usuário a deletado
	 * @return void
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * @param objDto
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		User obj = this.service.fromDTO(objDto);
		obj.setId(id);
		obj = this.service.update(obj);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Busca todos os posts de um usuário
	 *
	 * @param id Código do usuário a ser localizado os posts
	 * @return Retorna array de posts de um usuário
	 */
	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User obj = this.service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}

}
