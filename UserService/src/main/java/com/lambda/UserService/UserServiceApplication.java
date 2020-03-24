package com.lambda.UserService;

import com.lambda.UserService.Service.IUserService;
import com.lambda.UserService.model.entity.UserCredentials;
import com.lambda.UserService.model.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class UserServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(IUserService service) {
		return (args) -> {

			UserInfo userInfo = new UserInfo(null, "test", "test", "test");

			UserCredentials userCredentials = new UserCredentials(null, "test", "testtest", userInfo);
			var tmp = service.createUser(userCredentials);
		};
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
