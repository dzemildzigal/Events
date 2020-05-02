package com.lambda.UserTicketService.Configuration;


import com.lambda.UserTicketService.Grpc.CustomInterceptor;
import com.lambda.UserTicketService.Grpc.GRPCUserTicketServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    GRPCUserTicketServiceClient client;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor(client));

    }
}
