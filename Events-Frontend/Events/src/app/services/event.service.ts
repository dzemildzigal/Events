import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { map } from 'rxjs/operators';
import { LocalStorageService } from '../util/local-storage.service';
import { FilterDTO } from '../components/events/models/DTO/FilterDTO';
import { event } from '../components/events/models/interfaces/event';
import { EventWrapperDTO } from '../components/events/models/DTO/eventWrapperDTO';

const CONSTANTS: any = {
  getEventById:'event/',
  getEventsWithFilter: 'event/search',
  addEvent:'event/add-event'
};

@Injectable()
export class EventService {

  private eventApiBaseURL: string = environment.eventServiceAPI;
  
  constructor(private http: HttpClient,
              private localStorage: LocalStorageService) {
               }
  
  public addEvent(newEvent:EventWrapperDTO): Observable<any> {
    return this.http.post(this.eventApiBaseURL + CONSTANTS.addEvent,newEvent);
  }


  public getEventsByFilter(eventFilterDTO: FilterDTO): Observable<any> {
    return this.http.post(this.eventApiBaseURL + CONSTANTS.getEventsWithFilter, eventFilterDTO);
  }

  public getEventById(eventId: Number): Observable<any> {
    return this.http.get(this.eventApiBaseURL + CONSTANTS.getEventById + eventId.toString());
  }
}
