package com.lambda.UserService.Repository;

import com.lambda.UserService.Model.Entity.UserCredentials;
import org.springframework.data.repository.CrudRepository;

public interface IUserCredentialsRepository extends CrudRepository<UserCredentials, Long> {
     UserCredentials findByUsername(String username);
     UserCredentials findByUserUserId (long id);
}
