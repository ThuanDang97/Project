import { TestBed } from '@angular/core/testing';

import { LoadCssService } from './load-css.service';

describe('LoadCssService', () => {
  let service: LoadCssService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoadCssService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
