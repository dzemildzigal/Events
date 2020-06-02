import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-simple-popup',
  templateUrl: './simple-popup.component.html',
  styleUrls: ['./simple-popup.component.scss']
})
export class SimplePopupComponent implements OnInit {

  public message: string;
  constructor(
    private matDialogRef: MatDialogRef<SimplePopupComponent>
  ) { }

  ngOnInit(): void {
  }

  public confirm(): void {
    this.matDialogRef.close(true);
  }

}
