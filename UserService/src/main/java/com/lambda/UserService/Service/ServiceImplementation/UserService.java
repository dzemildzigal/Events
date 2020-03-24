package com.lambda.UserService.Service.ServiceImplementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambda.UserService.Security.JwtUtil;
import com.lambda.UserService.Service.IUserService;
import com.lambda.UserService.model.api.UserLoginAckDTO;
import com.lambda.UserService.model.api.UserLoginDTO;
import com.lambda.UserService.model.entity.UserCredentials;
import com.lambda.UserService.model.entity.UserInfo;
import com.lambda.UserService.repository.IUserCredentialsRepository;
import com.lambda.UserService.repository.IUserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

import static com.lambda.UserService.Security.SecurityConstants.HEADER_PREFIX;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IUserCredentialsRepository userCredentialsRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsService userAuthDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public UserInfo createUser(UserCredentials userDTO) {

       userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        var userCred = userCredentialsRepository.save(userDTO);
        return userCred.getUser();
    }

    @Override
    public UserInfo findById(long id) throws Exception {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new Exception("UserInfo for id:" + id + "not found");
        }
    }

    @Override
    public UserInfo updateUserInfo(UserInfo info) {
        return this.userRepository.save(info);
    }

    @Override
    public UserLoginAckDTO login(UserLoginDTO userLoginDTO) {
        try {
            String test;
            boolean bla = this.bCryptPasswordEncoder.matches("test", this.bCryptPasswordEncoder.encode("test"));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDTO.getUsername(),
                            userLoginDTO.getPassword(),
                            new ArrayList<>())
            );
        } catch (Exception e ) {
            e.printStackTrace();
            return new UserLoginAckDTO(false, "nan");
        }

         final UserDetails userDetails = this.userAuthDetailsService.loadUserByUsername(userLoginDTO.getUsername());
         final String jwt = jwtUtil.generateToken(userDetails);
         return  new UserLoginAckDTO(true, jwt);
    }

    @Override
    public boolean isUserAuthorized(long userId, String token) {
        String username = null;
        String jwt = null;

        if (token != null && token.startsWith(HEADER_PREFIX)) {
            jwt = token.substring(7);
            username = jwtUtil.extractUsername(jwt);
        } else {
            return false;
        }
        if (jwtUtil.isTokenExpired(jwt)) {
            return false;
        }
        if (username != null) {
            UserInfo userInfo = userRepository.findById(userId);
            UserCredentials userCredentials =  userCredentialsRepository.findByUsername(username);
            if (userInfo == null || userCredentials == null || !userCredentials.getUser().getUserId().equals(userInfo.getUserId())) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(long userId) {
        UserCredentials userCredentials = userCredentialsRepository.findByUserUserId(userId);
        userCredentialsRepository.delete(userCredentials);
        return true;
    }
}
