package com.lambda.EventService.Services;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.EnuEventStatus;

public interface IEnuEventStatusService {
    EnuEventStatus createEnuEventStatus(EnuEventStatus object)throws CustomEventException;
    EnuEventStatus findById(Long id)throws CustomEventException;
    EnuEventStatus updateEnuEventStatus(EnuEventStatus updateVal)throws CustomEventException;
    EnuEventStatus findByDescription(String description)throws CustomEventException;
}
