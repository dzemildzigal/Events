package com.lambda.UserService.repository;

import com.lambda.UserService.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserRepository extends CrudRepository<UserInfo, Long> {
    UserInfo findById(long id);
    List<UserInfo> findByEmail(String email);
}
