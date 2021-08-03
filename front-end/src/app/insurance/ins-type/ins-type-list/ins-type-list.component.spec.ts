import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsTypeListComponent } from './ins-type-list.component';

describe('InsTypeListComponent', () => {
  let component: InsTypeListComponent;
  let fixture: ComponentFixture<InsTypeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsTypeListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InsTypeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
