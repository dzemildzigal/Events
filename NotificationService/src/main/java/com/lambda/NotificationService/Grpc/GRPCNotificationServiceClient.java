package com.lambda.NotificationService.Grpc;

import com.lambda.grpc.systemevent.Ack;
import com.lambda.grpc.systemevent.SystemEventMessage;
import com.lambda.grpc.systemevent.SystemEventsServiceGrpc;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class GRPCNotificationServiceClient {


    @Autowired
    EurekaClient eurekaClient;
    SystemEventsServiceGrpc.SystemEventsServiceStub systemEventsServiceStub;



    public void createSystemEvent(SystemEventMessage systemEventMessage){

        StreamObserver<Ack> streamObserver = new StreamObserver<Ack>() {
            @Override
            public void onNext(Ack ack) {
                System.out.println("Logged"+ack.toString());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error "+throwable.getLocalizedMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Completed everything");
            }
        };
        if(systemEventsServiceStub == null)
            this.initializeClient();
        if(systemEventsServiceStub == null)
            return;


        systemEventsServiceStub.saveSystemEvent(systemEventMessage,streamObserver);


    }


    @PostConstruct
    private void initializeClient(){
        try {
            final InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("SystemEvent", false);
            ManagedChannel channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(), instanceInfo.getPort()).usePlaintext().build();
            systemEventsServiceStub = SystemEventsServiceGrpc.newStub(channel);
        }catch (Exception ex){}
    }
}
