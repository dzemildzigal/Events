package com.lambda.UserService.repository;

import com.lambda.UserService.model.UserCredentials;
import com.lambda.UserService.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface IUserCredentialsRepository extends CrudRepository<UserCredentials, Long> {

}
