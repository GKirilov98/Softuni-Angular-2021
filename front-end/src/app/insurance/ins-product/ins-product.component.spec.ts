import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsProductComponent } from './ins-product.component';

describe('InsProductComponent', () => {
  let component: InsProductComponent;
  let fixture: ComponentFixture<InsProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsProductComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InsProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
