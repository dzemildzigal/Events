package com.lambda.EventService.Services;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Entity.EnuRegistrationType;

import java.util.List;

public interface IEnuRegistrationTypeService {
    EnuRegistrationType createEnuRegistrationType(EnuRegistrationType object)throws CustomEventException;
    EnuRegistrationType findById(Long id)throws CustomEventException;
    List<EnuRegistrationType> findByDescription(String desc)throws CustomEventException;
}
