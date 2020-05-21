import { Component, OnInit } from '@angular/core';
import { event } from "./models/interfaces/event";
import { eventComment } from "./models/interfaces/eventComment";
import { userEventRegistration } from "./models/interfaces/userEventRegistration";
import { EventService } from 'src/app/services/event.service';
import { LocalStorageService } from 'src/app/util/local-storage.service';
import { Router } from '@angular/router';
import { FilterDTO } from './models/DTO/FilterDTO'
import { EventWrapperDTO } from './models/DTO/eventWrapperDTO';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  filterResponse:event[]=[];
  public events: any[] = [];
  addEventFormResponse:event;

  constructor(
    public eventService: EventService,
    public localStorage: LocalStorageService,
    public router: Router
  ){

  }

  ngOnInit():void{
    //this.updateEvents({});
  }

  public addEvent(newEvent:EventWrapperDTO):void {
    this.eventService.addEvent(newEvent).subscribe(res=>{
      if(res!=null){
        this.events=res;
      }
    });
  }

  public updateEvents(filter: FilterDTO): void {  
    this.eventService.getEventsByFilter(filter).subscribe(res => {
      if(res!=null)
        this.events=res;
  });
  }
}
