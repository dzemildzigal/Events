import { Component, OnInit } from '@angular/core';
import { event } from "./models/interfaces/event";
import { eventComment } from "./models/interfaces/eventComment";
import { userEventRegistration } from "./models/interfaces/userEventRegistration";

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {
  events:any[]=[{
    eventId:null,
    eventName:"Radno vrijeme, promocija albuma",
    description:"Promocija debitanskog albuma benda Radno vrijeme pod nazivom \"0-24\".",
    location:{
      locationId:null,
      description:"Dom mladih, Skenderija",
      eventList:Array<event>()
    },

    eventType:{
      eventTypeId:null,
      eventTypeDescription:"Promocija albuma",
      eventList:Array<event>()
    },

    canBuyTicket:true,
    ticketPrice:5.0,
    numberOfTicketsAvailable:200,
    eventPictureURL:"https://scontent.fsjj1-1.fna.fbcdn.net/v/t1.0-9/93787401_1638941006262500_3692810102256959488_o.jpg?_nc_cat=107&_nc_sid=dd9801&_nc_oc=AQnD0pHSnvtMz4UUcQ_-QJtsdjI_r-kkBmxjrA790yTvN4uuHo7nEQVyVvQAurqkt98&_nc_ht=scontent.fsjj1-1.fna&oh=156de3b0ba6e61a113ed2d0cb6b48e05&oe=5EE92794",
    createdByUserId:1,

    enuEventStatus:{
      eventStatusId:null,
      description:"Aktivan",
      events:Array<event>()
    },
    eventTime: new Date(Date.now()),
    eventCommentsList:Array<eventComment>(),
    userEventRegistrationList:Array<userEventRegistration>()
  },{
    eventId:null,
    eventName:"Radno vrijeme, promocija albuma",
    description:"Promocija debitanskog albuma benda Radno vrijeme pod nazivom \"0-24\".",
    location:{
      locationId:null,
      description:"Dom mladih, Skenderija",
      eventList:Array<event>()
    },

    eventType:{
      eventTypeId:null,
      eventTypeDescription:"Promocija albuma",
      eventList:Array<event>()
    },

    canBuyTicket:true,
    ticketPrice:5.0,
    numberOfTicketsAvailable:200,
    eventPictureURL:"https://scontent.fsjj1-1.fna.fbcdn.net/v/t1.0-9/93787401_1638941006262500_3692810102256959488_o.jpg?_nc_cat=107&_nc_sid=dd9801&_nc_oc=AQnD0pHSnvtMz4UUcQ_-QJtsdjI_r-kkBmxjrA790yTvN4uuHo7nEQVyVvQAurqkt98&_nc_ht=scontent.fsjj1-1.fna&oh=156de3b0ba6e61a113ed2d0cb6b48e05&oe=5EE92794",
    createdByUserId:1,

    enuEventStatus:{
      eventStatusId:null,
      description:"Aktivan",
      events:Array<event>()
    },
    eventTime: new Date(Date.now()),
    eventCommentsList:Array<eventComment>(),
    userEventRegistrationList:Array<userEventRegistration>()
  }];
  constructor() { }

  ngOnInit(): void {
  }

}
