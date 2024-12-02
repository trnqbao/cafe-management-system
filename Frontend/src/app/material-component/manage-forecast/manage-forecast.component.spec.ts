import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageForecastComponent } from './manage-forecast.component';

describe('ManageForecastComponent', () => {
  let component: ManageForecastComponent;
  let fixture: ComponentFixture<ManageForecastComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageForecastComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageForecastComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
