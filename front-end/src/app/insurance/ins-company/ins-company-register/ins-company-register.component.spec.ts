import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsCompanyRegisterComponent } from './ins-company-register.component';

describe('InsCompanyRegisterComponent', () => {
  let component: InsCompanyRegisterComponent;
  let fixture: ComponentFixture<InsCompanyRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsCompanyRegisterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InsCompanyRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
