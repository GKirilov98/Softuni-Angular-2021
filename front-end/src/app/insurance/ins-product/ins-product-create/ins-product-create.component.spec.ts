import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsProductCreateComponent } from './ins-product-create.component';

describe('InsProductCreateComponent', () => {
  let component: InsProductCreateComponent;
  let fixture: ComponentFixture<InsProductCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsProductCreateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InsProductCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
