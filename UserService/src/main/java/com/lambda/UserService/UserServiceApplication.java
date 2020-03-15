package com.lambda.UserService;

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
	public CommandLineRunner demo(IUserRepository repository) {
		return (args) -> {

			UserInfo a = new UserInfo();
			a.setEmail("test");
			a.setFullName("test");
			repository.save(a);
			List<UserInfo> test = repository.findByEmail("test");
		};
	}
}
