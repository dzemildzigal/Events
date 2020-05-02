package com.lambda.NotificationService.Configuration;
import com.lambda.NotificationService.Grpc.CustomInterceptor;
import com.lambda.NotificationService.Grpc.GRPCNotificationServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    GRPCNotificationServiceClient client;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor(client));

    }
}
