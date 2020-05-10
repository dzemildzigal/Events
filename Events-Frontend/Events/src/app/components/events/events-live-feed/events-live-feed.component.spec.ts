import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventsLiveFeedComponent } from './events-live-feed.component';

describe('EventsLiveFeedComponent', () => {
  let component: EventsLiveFeedComponent;
  let fixture: ComponentFixture<EventsLiveFeedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventsLiveFeedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventsLiveFeedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
