import { TestBed } from '@angular/core/testing';

import { AccountIdService } from './account-id.service';

describe('AccountIdService', () => {
  let service: AccountIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccountIdService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
