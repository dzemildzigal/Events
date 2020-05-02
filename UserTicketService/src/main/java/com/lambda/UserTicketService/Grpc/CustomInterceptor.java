package com.lambda.UserTicketService.Grpc;

import com.lambda.grpc.systemevent.SystemEventMessage;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@NoArgsConstructor
public class CustomInterceptor extends HandlerInterceptorAdapter {
    GRPCUserTicketServiceClient grpcUserTicketServiceClient;
    public CustomInterceptor(GRPCUserTicketServiceClient client){
        this.grpcUserTicketServiceClient =  client;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception
    {
        String authToken = request.getHeader("Authorization");
        if (authToken == null) {
            authToken = "";
        }
        var systemEventMessage = SystemEventMessage.newBuilder().setUserAuthToken(authToken).
                setTimeStamp(LocalDateTime.now().toString()).setActionResult(Integer.toString(response.getStatus())).
                setActionType(request.getMethod()).setServiceName("UserTicketService").setResourceObject(request.getRequestURI()).build();

        grpcUserTicketServiceClient.createSystemEvent(systemEventMessage);
        System.out.println("MINIMAL: INTERCEPTOR AFTERCOMPLETION CALLED");
    }
}
