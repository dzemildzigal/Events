package com.lambda.UserService.Controller;


import com.lambda.UserService.Service.IUserService;
import com.lambda.UserService.model.api.UserLoginAckDTO;
import com.lambda.UserService.model.api.UserLoginDTO;
import com.lambda.UserService.model.entity.UserCredentials;
import com.lambda.UserService.model.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/{id}")
    public UserInfo getUserInfo(@PathVariable long id) {
        return this.userService.findById(id);
    }

    @PostMapping("/sign-up")
    public UserInfo registerUser (@RequestBody UserCredentials info) {
        return this.userService.createUser(info);
    }

    @PutMapping("/update")
    public UserInfo updateUserInfo (@RequestBody UserInfo info) {
        return  this.userService.updateUserInfo(info);
    }

    @PostMapping("/sign-in")
    public UserLoginAckDTO userLogin (UserLoginDTO userLoginDTO) {
        return  this.userService.login(userLoginDTO);
    }
}
