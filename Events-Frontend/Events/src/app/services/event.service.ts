import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { map } from 'rxjs/operators';
import { LocalStorageService } from '../util/local-storage.service';

const CONSTANTS: any = {
  getEventById:'event/',
  getEventsWithFilter: 'event/search'
};

@Injectable()
export class EventService {

  private eventApiBaseURL: string = environment.eventServiceAPI;
  
  constructor(private http: HttpClient,
              private localStorage: LocalStorageService) {
               }

  public getEventsByFilter(eventFilterDTO: any): Observable<any> {
    return this.http.post(this.eventApiBaseURL + CONSTANTS.getEventsWithFilter, eventFilterDTO);
  }

  public getEventById(eventId: any): Observable<any> {
    return this.http.get(this.eventApiBaseURL + CONSTANTS.getEventById+eventId);
  }
}
