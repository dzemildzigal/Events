import { Component, OnInit } from '@angular/core';
import { event } from "./models/interfaces/event";
import { eventComment } from "./models/interfaces/eventComment";
import { userEventRegistration } from "./models/interfaces/userEventRegistration";
import { EventService } from 'src/app/services/event.service';
import { LocalStorageService } from 'src/app/util/local-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  public events: any[] = [];

  constructor(
    public eventService: EventService,
    public localStorage: LocalStorageService,
    public router: Router
  ){

  }


  public filter: any ={
    eventName:"RV"
  };

  ngOnInit():void{
    // this.updateEvents({});
  }

  public filterData(): void {
     this.updateEvents({});
  }

  public updateEvents(filterDTO: any): void {
    this.eventService.getEventsByFilter(this.filter).subscribe(res => {
      this.events = res;
  });
  }
}
