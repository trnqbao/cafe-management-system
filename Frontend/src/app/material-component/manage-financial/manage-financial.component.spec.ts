import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageFinancialComponent } from './manage-financial.component';

describe('ManageFinancialComponent', () => {
  let component: ManageFinancialComponent;
  let fixture: ComponentFixture<ManageFinancialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageFinancialComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageFinancialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
