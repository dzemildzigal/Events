package com.lambda.EventService.Controllers;


import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Helpers.UserServiceHelper;
import com.lambda.EventService.Models.Entity.EnuRegistrationType;
import com.lambda.EventService.Models.Entity.UserEventRegistration;
import com.lambda.EventService.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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
    @Autowired
    UserServiceHelper userServiceHelper;

    //Get Event registration by its ID
    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserEventRegistration getUserEventRegistrationById(@PathVariable long id) throws CustomEventException {
        return userEventRegistrationService.findById(id);
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
    public List<UserEventRegistration> getEventRegistrationByUserId(@PathVariable Long userId, @RequestHeader(value = "Authorization") String authorizationToken) throws CustomEventException{
        if(userServiceHelper.CheckUserAuthorised(userId.toString(),authorizationToken)) {
            return userEventRegistrationService.findByUserId(userId);
        }
        throw new CustomEventException("500: Unexpected outcome of method getEventRegistrationByUserId");
    }

    //Register the user with userId to the event with eventId and type of registration with regTypeString
    @PostMapping(path = "{eventId}/registerUser/{userId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserEventRegistration registerUserToEventByUserIdEventId(@PathVariable Long eventId, @RequestParam String regTypeString, @PathVariable Long userId, @RequestHeader(value = "Authorization") String authorizationToken) throws CustomEventException{
        if(userServiceHelper.CheckUserAuthorised(userId.toString(),authorizationToken)) {
            var event = eventService.findById(eventId);
            var enuRegTypeArray = enuRegistrationTypeService.findByDescription(regTypeString);
            if(enuRegTypeArray.size()==0) throw new CustomEventException("404: No Registration types described by: "+regTypeString);
            var enuRegType = enuRegTypeArray.get(0);
            var newEventReg = new UserEventRegistration();
            newEventReg.setEnuRegistrationType(enuRegType);
            newEventReg.setEvent(event);
            newEventReg.setUserId(userId);
            return userEventRegistrationService.createUserEventRegistration(newEventReg);
        }
        throw new CustomEventException("500: Unexpected outcome of method registerUserToEventByUserIdEventId");
    }
}
