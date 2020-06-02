import { Component, OnInit, ViewChild, EventEmitter, Output } from '@angular/core';
import { MatMenuTrigger } from '@angular/material/menu';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { event } from '../../models/interfaces/event';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { map } from 'rxjs/operators';
import { EventHandlerVars } from '@angular/compiler/src/compiler_util/expression_converter';
import { location } from '../../models/interfaces/location';
import { enuEventStatus } from '../../models/interfaces/enuEventStatus';
import { eventType } from '../../models/interfaces/eventType';
import { LocalStorageService } from 'src/app/util/local-storage.service';
import { EventWrapperDTO } from '../../models/DTO/eventWrapperDTO';
import { MatDialogRef } from '@angular/material/dialog';


@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.scss']
})
export class AddEventComponent implements OnInit {
  public newEventFormGroup: FormGroup;
  public event: event;
  constructor(private localStorageService: LocalStorageService,
              public dialogRef: MatDialogRef<AddEventComponent> ) { 

  }
  
  OnChange(event){
    console.log(event); 
  }

  OnIndeterminateChange(event){
    console.log(event); 
  }
  
  ngOnInit(): void {

    this.newEventFormGroup = new FormGroup (
      {
        eventName: new FormControl(null, [Validators.required, Validators.minLength(3)]),
        eventPictureURL: new FormControl(null, [Validators.required, Validators.minLength(3)]),
        locationName: new FormControl(null, [Validators.required, Validators.minLength(3)]),
        eventDate: new FormControl(null, [Validators.required, Validators.minLength(3)]),
        canBuyTickets: new FormControl(false),
        ticketPrice: new FormControl(null),
        numberOfTicketsAvailable: new FormControl(null),
        eventTypeDescription: new FormControl(null, [Validators.required, Validators.minLength(3)]),
        eventDescription: new FormControl(null, [Validators.required, Validators.minLength(3)]),
        eventStatusDescription: new FormControl(null, [Validators.required, Validators.minLength(3)])

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

    this.dialogRef.close(EventWrapper);
  }
}
