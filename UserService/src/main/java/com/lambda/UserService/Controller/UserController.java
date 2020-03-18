package com.lambda.UserService.Controller;


import com.lambda.UserService.Service.IUserService;
import com.lambda.UserService.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/{id}")
    public UserInfo getUserById(@PathVariable long id) {
        return this.userService.findById(id);
    }
}
