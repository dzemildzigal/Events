package com.lambda.EventService.Services;

import com.lambda.EventService.models.EnuRegistrationType;

public interface IEnuRegistrationTypeService {
    void createEnuRegistrationType(EnuRegistrationType object);
    EnuRegistrationType findById(long id);
}
