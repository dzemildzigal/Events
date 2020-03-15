package com.lambda.UserService.Service.ServiceImplementation;

import com.lambda.UserService.Service.IUserService;
import com.lambda.UserService.model.UserInfo;
import com.lambda.UserService.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public void createUser(UserInfo user) {
        userRepository.save(user);
    }

    @Override
    public UserInfo findById(long id) {
        return userRepository.findById(id);
    }
}
