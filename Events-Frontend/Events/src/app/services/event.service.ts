import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { map } from 'rxjs/operators';
import { LocalStorageService } from '../util/local-storage.service';
import { FilterDTO } from '../components/events/models/DTO/FilterDTO';
import { event } from '../components/events/models/interfaces/event';
import { EventWrapperDTO } from '../components/events/models/DTO/eventWrapperDTO';
import { eventComment } from '../components/events/models/interfaces/eventComment';
const CONSTANTS: any = {
  getEventById:'event/',
  getEventsWithFilter: 'event/search',
  addEvent: 'event/add-event',
  updateEvent: 'event/update-event',
  getComments: 'comment/event/',
  postComment: 'comment/post-comment/'
};

@Injectable()
export class EventService {

  private eventApiBaseURL: string = environment.eventServiceAPI;
  
  constructor(private http: HttpClient,
              private localStorage: LocalStorageService) {
               }
  
  public addEvent(newEvent:EventWrapperDTO): Observable<any> {
    return this.http.post(this.eventApiBaseURL + CONSTANTS.addEvent,  newEvent);
  }

  public updateEvent(updatedEvent:EventWrapperDTO): Observable<any> {
    return this.http.post(this.eventApiBaseURL + CONSTANTS.updateEvent + "?oldEventId="+JSON.stringify(updatedEvent.event.eventId), updatedEvent);
  }

  public getEventsByFilter(eventFilterDTO: FilterDTO): Observable<any> {
    return this.http.post(this.eventApiBaseURL + CONSTANTS.getEventsWithFilter, eventFilterDTO);
  }

  public getEventById(eventId: Number): Observable<any> {
    return this.http.get(this.eventApiBaseURL + CONSTANTS.getEventById + eventId.toString());
  }

  public getEventComments(eventId: Number): Observable<any> {
    return this.http.get(this.eventApiBaseURL + CONSTANTS.getComments + eventId.toString());
  }

  public postComment(commentText: string, event: EventWrapperDTO): Observable<any> {
    var commentObject: eventComment = {text:null,userId:null,eventCommentId:null,event:null};
    commentObject.text = commentText;
    commentObject.userId = this.localStorage.getUserInfo().userId;
    commentObject.event = event.event;
    return this.http.post(this.eventApiBaseURL + CONSTANTS.postComment + event.event.eventId.toString(), commentObject);
  }


}
