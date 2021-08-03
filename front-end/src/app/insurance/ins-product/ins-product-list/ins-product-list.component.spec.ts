import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsProductListComponent } from './ins-product-list.component';

describe('InsProductListComponent', () => {
  let component: InsProductListComponent;
  let fixture: ComponentFixture<InsProductListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsProductListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InsProductListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
