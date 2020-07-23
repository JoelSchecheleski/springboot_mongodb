package com.uppertools.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uppertools.workshop.domain.User;
import com.uppertools.workshop.dto.UserDTO;
import com.uppertools.workshop.repository.UserRepository;
import com.uppertools.workshop.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	/**
	 * Serviço responsável por buscar todos os usuários no banco de dados
	 * @return Retorna um array contendo todos os usuários cadaastrados
	 */
	public List<User> findAll() {
		return repository.findAll();
	}

	/**
	 * Busca um usuário por ID
	 * @param id Código do usuário a ser localizado
	 * @return Objeto User localizado
	 */
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	/**
	 * Cria um novo objeto no banco de dados
	 * @param obj Objeto do tipo usuário
	 * @return Retorna o objeto usuario inserido
	 */
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
