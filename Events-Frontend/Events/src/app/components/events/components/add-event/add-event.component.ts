import { Component, OnInit, ViewChild } from '@angular/core';
import { MatMenuTrigger } from '@angular/material/menu';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.scss']
})
export class AddEventComponent implements OnInit {
  formGroup: FormGroup;
  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;
  constructor() { }

  ngOnInit(): void {
    this.trigger.openMenu();
  }
  onSubmit():void{
    
    //const formGroupValue = this.formGroup.value;
    //this.filterResponse.emit(formGroupValue);
  }
}
