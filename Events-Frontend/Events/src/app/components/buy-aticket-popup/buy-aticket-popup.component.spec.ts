import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyATicketPopupComponent } from './buy-aticket-popup.component';

describe('BuyATicketPopupComponent', () => {
  let component: BuyATicketPopupComponent;
  let fixture: ComponentFixture<BuyATicketPopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuyATicketPopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuyATicketPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
