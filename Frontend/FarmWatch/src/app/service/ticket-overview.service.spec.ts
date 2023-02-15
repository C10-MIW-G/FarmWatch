import { TestBed } from '@angular/core/testing';

import { TicketOverviewService } from './ticket-overview.service';

describe('TicketOverviewService', () => {
  let service: TicketOverviewService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TicketOverviewService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
