import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DailyRevenueComponent } from './daily-revenue.component';

describe('DailyRevenueComponent', () => {
  let component: DailyRevenueComponent;
  let fixture: ComponentFixture<DailyRevenueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DailyRevenueComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DailyRevenueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
