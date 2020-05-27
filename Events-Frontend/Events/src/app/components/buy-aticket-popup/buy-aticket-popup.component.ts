import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { LocalStorageService } from 'src/app/util/local-storage.service';
import { UserTicketService } from 'src/app/services/user-ticket.service';

@Component({
  selector: 'app-buy-aticket-popup',
  templateUrl: './buy-aticket-popup.component.html',
  styleUrls: ['./buy-aticket-popup.component.scss']
})
export class BuyATicketPopupComponent implements OnInit {

  public event: any;
  public canBuyATicket: boolean;
  public formGroup: FormGroup;
  private userInfo: any;

  constructor(public dialogRef: MatDialogRef<BuyATicketPopupComponent>,
              private localStorageService: LocalStorageService,
              private userTicketService: UserTicketService) { }

  ngOnInit(): void {
    this.formGroup = new FormGroup({
      creditCardNumber: new FormControl(null, [Validators.required, Validators.minLength(9)])
    })
    this.canBuyATicket = this.event.canBuyTicket && this.event.numberOfTicketsAvailable > 0;
    this.userInfo = this.localStorageService.getUserInfo();
    console.log(this.event);
  }

  public buyATicket(): void {
    if (this.formGroup.valid) {
      
      const CCPayment: any = {
        ...this.formGroup.value,
        userTicket: {
          eventId: this.event.eventId,
          userId: this.userInfo.userId
        },
        amount: this.event.ticketPrice
      }
      this.dialogRef.close(this.userTicketService.buyATicket(CCPayment));
    }
  }
}
