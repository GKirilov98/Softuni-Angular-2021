import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsCompanyDetailsComponent } from './ins-company-details.component';

describe('InsCompanyDetailsComponent', () => {
  let component: InsCompanyDetailsComponent;
  let fixture: ComponentFixture<InsCompanyDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsCompanyDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InsCompanyDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
