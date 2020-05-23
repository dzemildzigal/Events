import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-buy-aticket-popup',
  templateUrl: './buy-aticket-popup.component.html',
  styleUrls: ['./buy-aticket-popup.component.scss']
})
export class BuyATicketPopupComponent implements OnInit {

  public event: any;

  constructor(public dialogRef: MatDialogRef<BuyATicketPopupComponent>) { }

  ngOnInit(): void {
  }

  public buyATicket(): void {
    this.dialogRef.close("Buy A ticket Called");
  }
}
