package com.lambda.UserService;

import com.lambda.UserService.Service.IUserService;
import com.lambda.UserService.model.UserCredentials;
import com.lambda.UserService.model.UserInfo;
import com.lambda.UserService.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(IUserService service) {
		return (args) -> {

			UserInfo userInfo = new UserInfo(null, "test", "test", "test");
			UserCredentials userCredentials = new UserCredentials(null, "test", "test", userInfo);
			var tmp = service.createUser(userCredentials);
		};
	}
}
