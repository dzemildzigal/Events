package com.lambda.EventService.Services;

import com.lambda.EventService.models.EnuRegistrationType;

public interface IEnuRegistrationTypeService {
    EnuRegistrationType createEnuRegistrationType(EnuRegistrationType object);
    EnuRegistrationType findById(Long id);
}
