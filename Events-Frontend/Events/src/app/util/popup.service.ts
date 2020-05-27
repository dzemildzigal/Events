import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BuyATicketPopupComponent } from '../components/buy-aticket-popup/buy-aticket-popup.component';
import { Observable } from 'rxjs';
import { EditEventComponent } from '../components/events/components/edit-event/edit-event.component';
import { EventWrapperDTO } from '../components/events/models/DTO/EventWrapperDTO';
import { EventService } from '../services/event.service';
import { AddEventComponent } from '../components/events/components/add-event/add-event.component';

@Injectable()
export class PopupService {


    constructor (private matDialog: MatDialog,
                 private eventService: EventService) {

    }
    
    public buyATicketPopup(event: any): Observable<any> {
        const dialog = this.matDialog.open(BuyATicketPopupComponent);
        dialog.componentInstance.event = event;
        return dialog.afterClosed();
    }

    public addEventPopup(): Observable<any>{
        const dialog = this.matDialog.open(AddEventComponent);
        if(dialog)
        return dialog.afterClosed();
        return dialog.beforeClosed();
    }

    public editEventPopup(event: EventWrapperDTO): Observable<any>{

        // izgubi se old event ID ovdje 
        const dialog = this.matDialog.open(EditEventComponent);
        dialog.componentInstance.eventWrapper = event;
        dialog.componentInstance.event = event.event;
        return dialog.afterClosed();
    }

  
}