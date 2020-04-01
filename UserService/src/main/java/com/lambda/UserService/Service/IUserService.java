package com.lambda.UserService.Service;

import com.lambda.UserService.model.api.UserLoginAckDTO;
import com.lambda.UserService.model.api.UserLoginDTO;
import com.lambda.UserService.model.entity.UserCredentials;
import com.lambda.UserService.model.entity.UserInfo;

public interface IUserService {
    UserInfo createUser(UserCredentials userDTO);
    UserInfo findById(long id) throws Exception;
    UserInfo updateUserInfo(UserInfo info);
    UserLoginAckDTO login(UserLoginDTO userLoginDTO);
    UserLoginAckDTO isUserAuthorized(long userId, String token);
    boolean deleteUser(long userId);
}
