package com.uppertools.workshop.services;

import java.util.List;
import java.util.Optional;

import com.uppertools.workshop.domain.User;
import com.uppertools.workshop.dto.UserDTO;
import com.uppertools.workshop.repository.UserRepository;
import com.uppertools.workshop.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	/**
	 * Serviço responsável por buscar todos os usuários no banco de dados
	 *
	 * @return Retorna um array contendo todos os usuários cadaastrados
	 */
	public List<User> findAll() {
		return this.repository.findAll();
	}

	/**
	 * Busca um usuário por ID
	 *
	 * @param id Código do usuário a ser localizado
	 * @return Objeto User localizado
	 */
	public User findById(String id) {
		Optional<User> obj = this.repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	/**
	 * Cria um novo objeto no banco de dados
	 *
	 * @param obj Objeto do tipo usuário
	 * @return Retorna o objeto usuario inserido
	 */
	public User insert(User obj) {
		return this.repository.insert(obj);
	}

	/**
	 * Elimina um registo com base no ID
	 *
	 * @param id Código do registro a ser deletado
	 */
	public void delete(String id) {
		findById(id);
		this.repository.deleteById(id);
	}

	/**
	 * Atualiza um objeto
	 * @param obj Objeto vindo da requisição
	 * @return Retorna o objeto atualizado
	 */
	public User update(User obj) {
		User newUser = findById(obj.getId());
		updateData(newUser, obj);
		return this.repository.save(newUser);
	}

	/**
	 * Atualiza newObjeto de objeto recebido como parâmetro
 	 * @param newUser Usuário buscado no banco de dados
	 * @param obj Objeto vindo da requisição
	 */
	private void updateData(User newUser, User obj) {
		newUser.setName(obj.getName());
		newUser.setEmail(obj.getEmail());
	}

	/**
	 * Converte objeto vindo da requisição para um para um User
	 *
	 * @param objDto Objeto DTO recebido na requisição
	 * @return Retorna um User criado
	 */
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}
