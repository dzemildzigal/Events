import { Component, OnInit } from '@angular/core';
import { EventService } from 'src/app/services/event.service';
import { LocalStorageService } from 'src/app/util/local-storage.service';
import { MatDialogRef } from '@angular/material/dialog';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss']
})
export class CommentsComponent implements OnInit {

  public newEventFormGroup: FormGroup;
  public comments = [];
  public eventId = -1;
  public commentText = "";
  public loggedIn = false;
  constructor(public eventService: EventService,
              public localStorage: LocalStorageService,
              public dialogRef: MatDialogRef<CommentsComponent>) { }
  
  ngOnInit(): void {
    if(this.localStorage.getUserInfo() != null){
      this.loggedIn = true;
    }
    this.newEventFormGroup = new FormGroup (
      {
        commentTextField: new FormControl(null)
      });
    this.eventService.getEventComments(this.eventId).subscribe(res => {
      if(res){
        this.comments = res;
      }
    });
    
    
  }
  onSubmit(): void{
    const formGroupValue = this.newEventFormGroup.value;
    this.commentText = formGroupValue;
    this.dialogRef.close(this.commentText);
  }
  

}
