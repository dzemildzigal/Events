package com.lambda.EventService.Services;

import com.lambda.EventService.Models.EnuRegistrationType;

import java.util.List;

public interface IEnuRegistrationTypeService {
    EnuRegistrationType createEnuRegistrationType(EnuRegistrationType object);
    EnuRegistrationType findById(Long id);
    List<EnuRegistrationType> findByDescription(String desc);
}
