import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BuyATicketPopupComponent } from '../components/buy-aticket-popup/buy-aticket-popup.component';
import { Observable } from 'rxjs';

@Injectable()
export class PopupService {


    constructor (private matDialog: MatDialog) {

    }
    public buyATicketPopup(event: any): Observable<any> {
        const dialog = this.matDialog.open(BuyATicketPopupComponent);
        dialog.componentInstance.event = event;
        return dialog.afterClosed();
    }
}