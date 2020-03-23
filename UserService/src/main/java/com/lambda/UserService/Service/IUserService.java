package com.lambda.UserService.Service;

import com.lambda.UserService.model.api.UserLoginAckDTO;
import com.lambda.UserService.model.api.UserLoginDTO;
import com.lambda.UserService.model.entity.UserCredentials;
import com.lambda.UserService.model.entity.UserInfo;

public interface IUserService {
    UserInfo createUser(UserCredentials userDTO);
    UserInfo findById(long id);
    UserInfo updateUserInfo(UserInfo info);
    UserLoginAckDTO login(UserLoginDTO userLoginDTO);
}
