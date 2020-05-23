package com.lambda.UserService.Service;

import com.lambda.UserService.Model.Api.UserLoginAckDTO;
import com.lambda.UserService.Model.Api.UserLoginDTO;
import com.lambda.UserService.Model.Entity.UserCredentials;
import com.lambda.UserService.Model.Entity.UserInfo;

public interface IUserService {
    UserInfo createOrUpdateUser(UserCredentials userDTO);
    UserInfo findById(long id) throws Exception;
    UserInfo updateUserInfo(UserInfo info);
    UserLoginAckDTO login(UserLoginDTO userLoginDTO);
    UserLoginAckDTO isUserAuthorized(long userId, String token);
    boolean deleteUser(long userId);
}
