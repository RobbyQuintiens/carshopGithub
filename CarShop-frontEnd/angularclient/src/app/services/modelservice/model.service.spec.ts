import { TestBed, inject } from '@angular/core/testing';

import { ModelService } from './model.service';

describe('ModelServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ModelService]
    });
  });

  it('should be created', inject([ModelService], (service: ModelService) => {
    expect(service).toBeTruthy();
  }));
});
