import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { LocalStorageService } from '../util/local-storage.service';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

const CONSTANTS = {
    createPayment: 'payment/create'
}
@Injectable()
export class UserTicketService {


    private userApiBaseURL: string = environment.userTicketServiceAPI;
  
    constructor(private http: HttpClient,
                private localStorage: LocalStorageService) {}

    public buyATicket (ccPayment: any): Observable<any> {
        return this.http.post(this.userApiBaseURL + CONSTANTS.createPayment, ccPayment);
    }
}