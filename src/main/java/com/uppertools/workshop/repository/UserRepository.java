package com.uppertools.workshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.uppertools.workshop.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
