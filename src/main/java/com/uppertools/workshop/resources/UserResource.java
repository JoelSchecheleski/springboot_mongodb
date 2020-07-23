package com.uppertools.workshop.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.uppertools.workshop.domain.User;
import com.uppertools.workshop.dto.UserDTO;
import com.uppertools.workshop.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	/**
	 * Busca todos os usuários no contexto GET
	 * @return Retorna um array contendo todos os usuários cadastrados
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}
	
	/**
	 * Busca um usuário pelo ID
	 * @param id Código do usuário a ser localizado
	 * @return Retorna um Usuário caso consiga localizar
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getById(@PathVariable String id){
		User obj = service.findById(id);
		return ResponseEntity.ok(new UserDTO(obj));
	}
	
	/**
	 * Cria um novo objeto
	 * @param obj Objeto recebido na requisição
	 * @return Retorna o objeto inserido
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
