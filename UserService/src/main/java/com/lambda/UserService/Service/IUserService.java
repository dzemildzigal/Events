package com.lambda.UserService.Service;

import com.lambda.UserService.model.UserCredentials;
import com.lambda.UserService.model.UserInfo;

public interface IUserService {
    UserInfo createUser(UserCredentials userDTO);
    UserInfo findById(long id);
}
