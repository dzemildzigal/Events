package com.lambda.UserService.Configuration;


import com.lambda.UserService.Grpc.CustomInterceptor;
import com.lambda.UserService.Grpc.GRPCEventServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    GRPCEventServiceClient client;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor(client));

    }


}
