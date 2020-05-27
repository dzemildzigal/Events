import { Component, OnInit, EventEmitter, Output } from '@angular/core';
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
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-edit-event',
  templateUrl: './edit-event.component.html',
  styleUrls: ['./edit-event.component.scss']
})
export class EditEventComponent implements OnInit {
  newEventFormGroup: FormGroup;
  event: event;
  eventWrapper: EventWrapperDTO;

  @Output() editEventFormResponse = new EventEmitter<any>();
  //TODO: ensure only the user that created an event can event click the edit.
  constructor(private localStorageService: LocalStorageService,
              public dialogRef: MatDialogRef<EditEventComponent>) { }

  ngOnInit(): void {
    this.newEventFormGroup = new FormGroup (
      {
        eventName: new FormControl(null),
        eventPictureURL: new FormControl(null),
        locationName: new FormControl(null),
        eventTime: new FormControl(null),
        canBuyTickets: new FormControl(false),
        ticketPrice: new FormControl(null),
        numberOfTicketsAvailable: new FormControl(null),
        eventTypeDescription: new FormControl(null),
        eventDescription: new FormControl(null),
        eventStatusDescription: new FormControl(null)

      }
    )
    let eventFormWrapper = {
        eventName: this.eventWrapper.event.eventName,
        eventPictureURL: this.eventWrapper.event.eventPictureURL,
        locationName: this.eventWrapper.location.description,
        eventTime: this.eventWrapper.event.eventTime,
        canBuyTickets: this.eventWrapper.event.canBuyTicket,
        ticketPrice: this.eventWrapper.event.ticketPrice,
        numberOfTicketsAvailable: this.eventWrapper.event.numberOfTicketsAvailable,
        eventTypeDescription: this.eventWrapper.eventType.eventTypeDescription,
        eventDescription: this.eventWrapper.event.description,
        eventStatusDescription: this.eventWrapper.enuEventStatus.description
    }

    this.newEventFormGroup.patchValue(eventFormWrapper);
  }
  public onSubmit(){
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
      eventId:this.event.eventId,
      eventName:eventData.eventName,
      description: formGroupValue.eventDescription,
      location: eventData.location,
      eventType: eventData.eventType,
      eventPictureURL: eventData.eventPictureURL,
      enuEventStatus: eventData.enuEventStatus,
      eventCommentsList: [],
      eventTime: formGroupValue.eventTime,
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
    this.eventWrapper = EventWrapper;
    this.dialogRef.close(EventWrapper);
  }
}
