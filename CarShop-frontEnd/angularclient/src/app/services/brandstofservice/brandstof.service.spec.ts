import { TestBed } from '@angular/core/testing';

import { BrandstofService } from './brandstof.service';

describe('BrandstofService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BrandstofService = TestBed.get(BrandstofService);
    expect(service).toBeTruthy();
  });
});
