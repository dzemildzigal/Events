package com.lambda.EventService.Services.ServiceImplementation;


import com.lambda.EventService.Services.IEnuEventStatusService;
import com.lambda.EventService.models.EnuEventStatus;
import com.lambda.EventService.repository.IEnuEventStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnuEventStatusService implements IEnuEventStatusService {

    @Autowired
    IEnuEventStatusRepository enuEventStatusRepository;


    @Override
    public EnuEventStatus createEnuEventStatus(EnuEventStatus object) {
        //test
        var enuEventStatusTemp = enuEventStatusRepository.save(object);
        return object;
    }

    @Override
    public EnuEventStatus findById(Long id) {
        long idd= id;
        return enuEventStatusRepository.findById(idd);
    }
}
