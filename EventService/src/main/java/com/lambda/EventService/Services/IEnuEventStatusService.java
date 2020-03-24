package com.lambda.EventService.Services;

import com.lambda.EventService.Models.EnuEventStatus;

public interface IEnuEventStatusService {
    EnuEventStatus createEnuEventStatus(EnuEventStatus object);
    EnuEventStatus findById(Long id);
    EnuEventStatus updateEnuEventStatus(EnuEventStatus updateVal);
}
