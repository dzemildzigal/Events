package com.lambda.EventService.Controllers;


import com.lambda.EventService.Models.EnuRegistrationType;
import com.lambda.EventService.Models.UserEventRegistration;
import com.lambda.EventService.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/{id}")
    public UserEventRegistration getUserEventRegistrationById(@PathVariable long id){
        var x=userEventRegistrationService.findById(id);
        return x;
    }
    //Get Event Registration type by Event registration ID
    @GetMapping("/type/{eventRegistrationId}")
    public EnuRegistrationType getEventRegistrationTypeByEventRegistrationId(@PathVariable long eventRegistrationId){
        var x = userEventRegistrationService.findById(eventRegistrationId);
        var regTypeId = x.getEnuRegistrationType().getEnuRegistrationTypeId();
        return enuRegistrationTypeService.findById(regTypeId);
    }

    //Get Event Registration by the User (user ID)
    @GetMapping("/user/{userId}")
    public List<UserEventRegistration> getEventRegistrationTypeByUserId(@PathVariable long userId) throws Exception{
        var x = userEventRegistrationService.findByUserId(userId);
        return x;
    }

    //Register the user with userId to the event with eventId and type of registration with regTypeString
    @PostMapping("/registerUser")
    public UserEventRegistration registerUserToEventByUserIdEventId(@RequestParam long userId, @RequestParam long eventId, @RequestParam String regTypeString) throws Exception{
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
