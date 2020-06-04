import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { event } from '../../models/interfaces/event';
import { eventComment } from '../../models/interfaces/eventComment';
import { enuEventStatus } from '../../models/interfaces/enuEventStatus';
import { userEventRegistration } from '../../models/interfaces/userEventRegistration';
import { stringify } from 'querystring';
import { LocalStorageService } from '../../../../util/local-storage.service';
import { PopupService } from 'src/app/util/popup.service';
import { NotificationsService } from 'src/app/services/notifications.service';
import { FilterDTO } from '../../models/DTO/FilterDTO';
import { EventWrapperDTO } from '../../models/DTO/eventWrapperDTO';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-event-info',
  templateUrl: './event-info.component.html',
  styleUrls: ['./event-info.component.scss']
})
export class EventInfoComponent implements OnInit {;
  @Output()
  public refresh: EventEmitter<any> = new EventEmitter();  
  @Input()
  public event: EventWrapperDTO;
  public eventInfoString:string="";
  public canEditEvent: boolean = false;
  public canBuyATicket: boolean = false;
  public canSubscribe: boolean = false;
  constructor(private popupService: PopupService,
              private notificationsService: NotificationsService,
              private eventService: EventService,
              private localStorageService: LocalStorageService) { }
         
              
  ngOnInit(): void {
    let userInfo = this.localStorageService.getUserInfo();
    if(userInfo){
      this.canEditEvent = this.event.userId == userInfo.userId;
      this.canBuyATicket = this.event.event.canBuyTicket;
      this.canSubscribe = userInfo != null;
    }
    var time: Date = new Date(this.event.event.eventTime);
    this.eventInfoString= this.event.event.description+
                          " will be held on location " +
                          this.event.location.description+
                          " on the day of "+
                          time.getDate()+"."+String((time.getMonth())+1)+"."+time.getFullYear()+"."+
                          (this.event.event.canBuyTicket==true?"Tickets can be bought and cost "+this.event.event.ticketPrice+"$ per piece.":"Tickets can not be bought."); 
  }

  public buyATicket(): void {
    this.popupService.buyATicketPopup(this.event.event).subscribe(resultOfDialog => {
      console.log("Dialog closed", resultOfDialog);
      if (resultOfDialog) {
         resultOfDialog.subscribe(res => {
           console.log(res);
         })
      }
    });
  }

  public subscribe(): void {
    const userinfo = this.localStorageService.getUserInfo();
        
    var subscription = {
      "userId" :  userinfo.userId,
      "eventTypeId" : this.event.eventType.eventTypeId
    }
    this.notificationsService.subscribeToEvent(subscription).subscribe(response => {
 
    })
  }

  public getComments(): any {
    this.popupService.getCommentsPopup(this.event).subscribe(dialogRes => {
      if(dialogRes != null && dialogRes != undefined){
        const commentText = dialogRes.commentTextField;
        this.eventService.postComment(commentText,this.event).subscribe(res=>{

        });

      }
    });
  }

  public editEvent(): any {
    this.popupService.editEventPopup(this.event).subscribe(dialogRes => {
      if(dialogRes){
        this.eventService.updateEvent(dialogRes).subscribe(response => {
          this.refresh.emit(null);
        })
      }
      
    });
  }

}
