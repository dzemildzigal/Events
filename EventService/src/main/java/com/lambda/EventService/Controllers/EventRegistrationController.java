package com.lambda.EventService.Controllers;


import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.EnuRegistrationType;
import com.lambda.EventService.Models.UserEventRegistration;
import com.lambda.EventService.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventRegistration")
public class EventRegistrationController {
    @Autowired
    IEventService eventService;
    @Autowired
    IEnuEventStatusService eventStatusService;
    @Autowired
    IEnuRegistrationTypeService enuRegistrationTypeService;
    @Autowired
    IEventTypeService eventTypeService;
    @Autowired
    IUserEventRegistrationService userEventRegistrationService;


    //Get Event registration by its ID
    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserEventRegistration getUserEventRegistrationById(@PathVariable long id) throws CustomEventException {
        var x=userEventRegistrationService.findById(id);
        return x;
    }
    //Get Event Registration type by Event registration ID
    @GetMapping(path = "/type/{eventRegistrationId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public EnuRegistrationType getEventRegistrationTypeByEventRegistrationId(@PathVariable long eventRegistrationId) throws CustomEventException{
        var x = userEventRegistrationService.findById(eventRegistrationId);
        var regTypeId = x.getEnuRegistrationType().getEnuRegistrationTypeId();
        return enuRegistrationTypeService.findById(regTypeId);
    }

    //Get Event Registration by the User (user ID)
    @GetMapping(path = "/user/{userId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserEventRegistration> getEventRegistrationTypeByUserId(@PathVariable long userId) throws CustomEventException{
        var x = userEventRegistrationService.findByUserId(userId);
        return x;
    }

    //Register the user with userId to the event with eventId and type of registration with regTypeString
    @PostMapping(path = "/registerUser",produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserEventRegistration registerUserToEventByUserIdEventId(@RequestParam long userId, @RequestParam long eventId, @RequestParam String regTypeString) throws CustomEventException{
        var event = eventService.findById(eventId);
        var enuRegTypeArray = enuRegistrationTypeService.findByDescription(regTypeString);
        var enuRegType = enuRegTypeArray.get(0);
        var newEventReg = new UserEventRegistration();
        newEventReg.setEnuRegistrationType(enuRegType);
        newEventReg.setEvent(event);
        newEventReg.setUserId(userId);
        return userEventRegistrationService.createUserEventRegistration(newEventReg);
    }
}
