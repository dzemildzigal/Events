import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-events-live-feed',
  templateUrl: './events-live-feed.component.html',
  styleUrls: ['./events-live-feed.component.scss']
})
export class EventsLiveFeedComponent implements OnInit {
  event = {
    id:1,
    name:"Svirka"
  }
  constructor() { }

  ngOnInit(): void {
  }

}
