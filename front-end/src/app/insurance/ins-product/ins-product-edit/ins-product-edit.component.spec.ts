import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsProductEditComponent } from './ins-product-edit.component';

describe('InsProductEditComponent', () => {
  let component: InsProductEditComponent;
  let fixture: ComponentFixture<InsProductEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsProductEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InsProductEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
