import { Component, OnInit, Input } from '@angular/core';
import { event } from '../../models/interfaces/event';
import { eventComment } from '../../models/interfaces/eventComment';
import { enuEventStatus } from '../../models/interfaces/enuEventStatus';
import { userEventRegistration } from '../../models/interfaces/userEventRegistration';
import { stringify } from 'querystring';
import { LocalStorageService } from '../../../../util/local-storage.service';

@Component({
  selector: 'app-event-info',
  templateUrl: './event-info.component.html',
  styleUrls: ['./event-info.component.scss']
})
export class EventInfoComponent implements OnInit {;
  @Input()
  event: any;
  public eventInfoString:string="";
  constructor() { }

  ngOnInit(): void {
    var time: Date = this.event.eventTime;
    this.eventInfoString= this.event.description+
                          " se održava na lokaciji " +
                          this.event.location.description+
                          " dana "+
                          time.getDate()+"."+String((time.getMonth())+1)+"."+time.getFullYear()+"."+
                          (this.event.canBuyTicket==true?"Karte se mogu kupiti i koštaju "+this.event.ticketPrice+"KM po komadu.":"Karte se ne mogu kupiti."); 
  }
  

}
