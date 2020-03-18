package com.lambda.EventService.Services;

import com.lambda.EventService.models.EnuEventStatus;

public interface IEnuEventStatusService {
    EnuEventStatus createEnuEventStatus(EnuEventStatus object);
    EnuEventStatus findById(long id);
}
