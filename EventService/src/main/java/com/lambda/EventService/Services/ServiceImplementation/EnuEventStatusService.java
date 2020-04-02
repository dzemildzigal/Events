package com.lambda.EventService.Services.ServiceImplementation;


import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Services.IEnuEventStatusService;
import com.lambda.EventService.Models.EnuEventStatus;
import com.lambda.EventService.Repositories.IEnuEventStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnuEventStatusService implements IEnuEventStatusService {

    @Autowired
    IEnuEventStatusRepository enuEventStatusRepository;


    @Override
    public EnuEventStatus createEnuEventStatus(EnuEventStatus object)throws CustomEventException {
        //test
        var enuEventStatusTemp = enuEventStatusRepository.save(object);
        return enuEventStatusTemp;
    }

    @Override
    public EnuEventStatus findById(Long id)throws CustomEventException {
        long idd= id;
        return enuEventStatusRepository.findById(idd);
    }

    @Override
    public EnuEventStatus updateEnuEventStatus(EnuEventStatus newVal)throws CustomEventException{

        if(newVal.getEventStatusId() != null) {
            long idd = newVal.getEventStatusId();
            var old = enuEventStatusRepository.findById(idd);
            old.setDescription(newVal.getDescription());
            old.setEvents(newVal.getEvents());
            return enuEventStatusRepository.save(old);
        }
        var newEntry = newVal;
        return enuEventStatusRepository.save(newEntry);
    }

    @Override
    public EnuEventStatus findByDescription(String description)throws CustomEventException{
        var statusOut = enuEventStatusRepository.findByDescription(description);
        return statusOut;
    }

}
