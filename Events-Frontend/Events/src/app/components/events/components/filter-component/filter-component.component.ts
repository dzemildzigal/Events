import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FilterDTO } from '../../models/DTO/FilterDTO';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { EventService } from 'src/app/services/event.service';
import { Router } from '@angular/router';
import { event } from '../../models/interfaces/event';

@Component({
  selector: 'app-filter-component',
  templateUrl: './filter-component.component.html',
  styleUrls: ['./filter-component.component.scss']
})
export class FilterComponentComponent implements OnInit {
  formGroup: FormGroup;
  filter:FilterDTO;

  @Output() filterResponse = new EventEmitter<any>();

  constructor(private eventService: EventService,
              private router: Router) { }

  ngOnInit(): void {
    this.formGroup = new FormGroup (
      {
        eventNameField: new FormControl(null),
        eventTypeDropDownMenuItem: new FormControl(null),
        locationNameField: new FormControl(null),
        eventDate: new FormControl(null)
      }
    )  
  }

  onSubmit():void{
      const formGroupValue = this.formGroup.value;
      this.filterResponse.emit(formGroupValue);
    }

}
