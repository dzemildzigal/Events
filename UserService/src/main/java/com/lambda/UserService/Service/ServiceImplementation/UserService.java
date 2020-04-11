package com.lambda.UserService.Service.ServiceImplementation;

import com.lambda.UserService.Security.JwtUtil;
import com.lambda.UserService.Service.IUserService;
import com.lambda.UserService.Model.Api.UserLoginAckDTO;
import com.lambda.UserService.Model.Api.UserLoginDTO;
import com.lambda.UserService.Model.Entity.UserCredentials;
import com.lambda.UserService.Model.Entity.UserInfo;
import com.lambda.UserService.Repository.IUserCredentialsRepository;
import com.lambda.UserService.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
       try {
           var userCred = userCredentialsRepository.save(userDTO);
           return userCred.getUser();
       } catch (Exception ex) {
           throw new IllegalArgumentException("Illegal arguments provided");
       }
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
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDTO.getUsername(),
                            userLoginDTO.getPassword(),
                            new ArrayList<>())
            );
        } catch (Exception e ) {
            //e.printStackTrace();
            return new UserLoginAckDTO(false, "nan");
        }

         final UserDetails userDetails = this.userAuthDetailsService.loadUserByUsername(userLoginDTO.getUsername());
         final String jwt = jwtUtil.generateToken(userDetails);
         return  new UserLoginAckDTO(true, jwt);
    }

    @Override
    public UserLoginAckDTO isUserAuthorized(long userId, String token) {
        String username = null;
        String jwt = null;
        UserLoginAckDTO dto = new UserLoginAckDTO(false, "nan");
        if (token != null && token.startsWith(HEADER_PREFIX)) {
            jwt = token.substring(7);
            username = jwtUtil.extractUsername(jwt);
        } else {
            return dto;
        }
        if (jwtUtil.isTokenExpired(jwt)) {
            return dto;
        }
        if (username != null) {
            UserInfo userInfo = userRepository.findById(userId);
            UserCredentials userCredentials =  userCredentialsRepository.findByUsername(username);
            if (userInfo == null || userCredentials == null || !userCredentials.getUser().getUserId().equals(userInfo.getUserId())) {
                return dto;
            }
            dto.setAuthenticated(true);
            dto.setToken(token);
        }
        return dto;
    }

    @Override
    public boolean deleteUser(long userId) {
        UserCredentials userCredentials = userCredentialsRepository.findByUserUserId(userId);
        userCredentialsRepository.delete(userCredentials);
        return true;
    }
}
