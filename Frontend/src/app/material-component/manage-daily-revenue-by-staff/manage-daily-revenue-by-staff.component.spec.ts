import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageDailyRevenueByStaffComponent } from './manage-daily-revenue-by-staff.component';

describe('ManageDailyRevenueByStaffComponent', () => {
  let component: ManageDailyRevenueByStaffComponent;
  let fixture: ComponentFixture<ManageDailyRevenueByStaffComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageDailyRevenueByStaffComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageDailyRevenueByStaffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
