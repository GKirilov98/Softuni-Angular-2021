import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsTypeCreateComponent } from './ins-type-create.component';

describe('InsTypeCreateComponent', () => {
  let component: InsTypeCreateComponent;
  let fixture: ComponentFixture<InsTypeCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsTypeCreateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InsTypeCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
