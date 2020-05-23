import { Component, OnInit, ViewChild, EventEmitter, Output } from '@angular/core';
import { MatMenuTrigger } from '@angular/material/menu';
import { FormGroup, FormControl } from '@angular/forms';
import { event } from '../../models/interfaces/event';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { map } from 'rxjs/operators';
import { EventHandlerVars } from '@angular/compiler/src/compiler_util/expression_converter';
import { location } from '../../models/interfaces/location';
import { enuEventStatus } from '../../models/interfaces/enuEventStatus';
import { eventType } from '../../models/interfaces/eventType';
import { LocalStorageService } from 'src/app/util/local-storage.service';
import { EventWrapperDTO } from '../../models/DTO/eventWrapperDTO';


@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.scss']
})
export class AddEventComponent implements OnInit {
  newEventFormGroup: FormGroup;
  event: event;
  @Output() addEventFormResponse = new EventEmitter<any>();
  constructor(private localStorageService: LocalStorageService) { 

  }
  
  OnChange(event){
    console.log(event); 
    //MatCheckboxChange {checked,MatCheckbox}
  }

  OnIndeterminateChange(event){
    console.log(event); 
    //true or false
  }
  
  ngOnInit(): void {
    //this.trigger.openMenu();
    this.newEventFormGroup = new FormGroup (
      {
        eventName: new FormControl(null),
        eventPictureURL: new FormControl(null),
        locationName: new FormControl(null),
        eventDate: new FormControl(null),
        canBuyTickets: new FormControl(null),
        ticketPrice: new FormControl(null),
        numberOfTicketsAvailable: new FormControl(null),
        eventTypeDescription: new FormControl(null),
        eventDescription: new FormControl(null),
        eventStatusDescription: new FormControl(null)

      }
    )
  }
  
  onSubmit():void{
    const formGroupValue = this.newEventFormGroup.value;
    var eventData: event = formGroupValue;
    eventData.createdByUserId = this.localStorageService.getUserInfo().userId;
    
    var eventTypeData: eventType = {
      eventTypeId:null,
      eventTypeDescription:formGroupValue.eventTypeDescription,
      eventList:[]
    }
    var enuEventStatusData: enuEventStatus= {
        eventStatusId:null,
        description:formGroupValue.eventStatusDescription,
        events:[]
    };
    var eventLocation:location= {
      locationId:null,
      description:formGroupValue.locationName,
      eventList:[]
    };
    eventData.location = eventLocation;
    eventData.eventCommentsList = [];
    eventData.enuEventStatus = enuEventStatusData;
    eventData.eventType = eventTypeData;
    eventData.userEventRegistrationList = [];
    
    var eventTemp:event={
      eventId:null,
      eventName:eventData.eventName,
      description: formGroupValue.eventDescription,
      location: eventData.location,
      eventType: eventData.eventType,
      eventPictureURL: eventData.eventPictureURL,
      enuEventStatus: eventData.enuEventStatus,
      eventCommentsList: [],
      eventTime: formGroupValue.eventDate,
      canBuyTicket: formGroupValue.canBuyTickets,
      ticketPrice: eventData.ticketPrice,
      numberOfTicketsAvailable: eventData.numberOfTicketsAvailable,
      createdByUserId: eventData.createdByUserId,
      userEventRegistrationList:[]
    }
    
    let EventWrapper:EventWrapperDTO= {event:null,enuEventStatus:null,eventType:null,location:null,userId:null};
    EventWrapper.event = eventTemp;
    EventWrapper.enuEventStatus = enuEventStatusData;
    EventWrapper.eventType = eventTypeData;
    EventWrapper.location = eventLocation;
    EventWrapper.userId = eventData.createdByUserId;

    this.addEventFormResponse.emit(EventWrapper);
  }
}
