package com.lambda.EventService.Services;

import com.lambda.EventService.models.EnuEventStatus;

public interface IEnuEventStatusService {
    void createEnuEventStatus(EnuEventStatus object);
    EnuEventStatus findById(long id);
}
