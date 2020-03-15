package com.lambda.UserService.Service;

import com.lambda.UserService.model.UserInfo;

public interface IUserService {
    void createUser(UserInfo user);
    UserInfo findById(long id);
}
