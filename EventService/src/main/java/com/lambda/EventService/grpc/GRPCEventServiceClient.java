package com.lambda.EventService.grpc;

import com.lambda.grpc.systemevent.Ack;
import com.lambda.grpc.systemevent.SystemEventMessage;
import com.lambda.grpc.systemevent.SystemEventsServiceGrpc;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.converters.Auto;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class GRPCEventServiceClient {

    @Autowired
    EurekaClientConfig eurekaClientConfig;
    @Autowired
    ManagedChannel managedChannel;

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



        systemEventsServiceStub.saveSystemEvent(systemEventMessage,streamObserver);


    }


    @PostConstruct
    private void initializeClient(){
        systemEventsServiceStub = SystemEventsServiceGrpc.newStub(managedChannel);
    }

}
