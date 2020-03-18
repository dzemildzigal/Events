package com.lambda.UserService.Service.ServiceImplementation;

import com.lambda.UserService.Service.IUserService;
import com.lambda.UserService.model.UserCredentials;
import com.lambda.UserService.model.UserInfo;
import com.lambda.UserService.repository.IUserCredentialsRepository;
import com.lambda.UserService.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IUserCredentialsRepository userCredentialsRepository;

    @Override
    public UserInfo createUser(UserCredentials userDTO) {
        // testing
        //UserInfo user = userRepository.save(userDTO.getUserInfo());

        var userCred = userCredentialsRepository.save(userDTO);
        return userCred.getUser();
    }

    @Override
    public UserInfo findById(long id) {
        return userRepository.findById(id);
    }
}
