import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { BrandUpdateComponent } from './brand-update.component';

describe('BrandUpdateComponent', () => {
  let component: BrandUpdateComponent;
  let fixture: ComponentFixture<BrandUpdateComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ BrandUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrandUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
