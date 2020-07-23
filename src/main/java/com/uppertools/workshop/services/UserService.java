package com.uppertools.workshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uppertools.workshop.domain.User;
import com.uppertools.workshop.repository.UserRepository;

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

}
