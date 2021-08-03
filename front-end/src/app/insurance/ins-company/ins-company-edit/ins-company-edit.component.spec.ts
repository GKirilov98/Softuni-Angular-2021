import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsCompanyEditComponent } from './ins-company-edit.component';

describe('InsCompanyEditComponent', () => {
  let component: InsCompanyEditComponent;
  let fixture: ComponentFixture<InsCompanyEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsCompanyEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InsCompanyEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
