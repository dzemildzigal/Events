package com.lambda.UserService.Controller;


import com.lambda.UserService.Security.JwtUtil;
import com.lambda.UserService.Service.IUserService;
import com.lambda.UserService.model.api.UserLoginAckDTO;
import com.lambda.UserService.model.api.UserLoginDTO;
import com.lambda.UserService.model.entity.UserCredentials;
import com.lambda.UserService.model.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    IUserService userService;


    @ApiOperation("Get user info by userId")
    @GetMapping("user-info/{id}")
    public UserInfo getUserInfo(@PathVariable long id) throws Exception {
        return this.userService.findById(id);
    }

    @ApiOperation("Register user")
    @PostMapping("user-info/sign-up")
    public UserInfo registerUser (@RequestBody UserCredentials info) {
        return this.userService.createUser(info);
    }

    @ApiOperation("Update user info")
    @PutMapping("/user-info/update")
    public UserInfo updateUserInfo (@RequestBody UserInfo info) {
        return  this.userService.updateUserInfo(info);
    }

    @ApiOperation("Sign in")
    @PostMapping("/sign-in")
    public UserLoginAckDTO userLogin (@RequestBody UserLoginDTO userLoginDTO) {
        return  this.userService.login(userLoginDTO);
    }

    @ApiOperation("Check if the user is authorized by userId and Auth token")
    @GetMapping("/is-user-authorized/{userId}")
    public UserLoginAckDTO isUserAuthorized (@PathVariable long userId, @RequestHeader(value = "Authorization") String authorization) {
        return this.userService.isUserAuthorized(userId, authorization);
    }

    @ApiOperation("Delete user by user id")
    @PostMapping("delete/{userId}")
    public boolean deleteUser(@PathVariable long userId) {
        return this.userService.deleteUser(userId);
    }
}
