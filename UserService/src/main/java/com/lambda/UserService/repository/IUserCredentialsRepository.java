package com.lambda.UserService.repository;

import com.lambda.UserService.model.entity.UserCredentials;
import org.springframework.data.repository.CrudRepository;

public interface IUserCredentialsRepository extends CrudRepository<UserCredentials, Long> {
     UserCredentials findByUsername(String username);
     UserCredentials findByUserUserId (long id);
}
