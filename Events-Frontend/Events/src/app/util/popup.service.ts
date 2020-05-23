import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BuyATicketPopupComponent } from '../components/buy-aticket-popup/buy-aticket-popup.component';

@Injectable()
export class PopupService {


    constructor (private matDialog: MatDialog) {

    }
    public buyATicketPopup(event: any) {
        const dialog = this.matDialog.open(BuyATicketPopupComponent);
        dialog.componentInstance.event = event;
    }
}