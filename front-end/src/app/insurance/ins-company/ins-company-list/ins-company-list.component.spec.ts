import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsCompanyListComponent } from './ins-company-list.component';

describe('InsCompanyListComponent', () => {
  let component: InsCompanyListComponent;
  let fixture: ComponentFixture<InsCompanyListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsCompanyListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InsCompanyListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
