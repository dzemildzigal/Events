package com.example.SystemEvents.Services;

import com.example.SystemEvents.Entity.SystemEvent;
import com.example.SystemEvents.Repository.ISystemEventRepository;
import com.lambda.grpc.systemevent.Ack;
import com.lambda.grpc.systemevent.SystemEventMessage;
import com.lambda.grpc.systemevent.SystemEventsServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@GrpcService
public class SystemEventsServiceImplementation extends SystemEventsServiceGrpc.SystemEventsServiceImplBase {

    @Autowired
    ISystemEventRepository iSystemEventRepository;


    @Override
    public void saveSystemEvent(SystemEventMessage request, StreamObserver<Ack> responseObserver) {
        LocalDateTime dateTime = LocalDateTime.parse(request.getTimeStamp());


        SystemEvent systemEvent = new SystemEvent(null, dateTime , request.getServiceName(), Long.valueOf(request.getUserId()), request.getActionType(), request.getResourceObject(), request.getActionResult());
        iSystemEventRepository.save(systemEvent);
        Ack.Builder builder = Ack.newBuilder();
        builder.setMessage("System Event Saved");
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
