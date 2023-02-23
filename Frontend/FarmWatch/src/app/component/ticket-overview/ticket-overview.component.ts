import { Component, OnInit } from '@angular/core';
import { Ticket } from 'src/app/model/ticket';
import { TicketOverviewService } from 'src/app/service/ticket-overview.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ToastService } from 'src/app/service/toast.service';
import { Sort } from '@angular/material/sort';

@Component({
  selector: 'app-ticket-overview',
  templateUrl: './ticket-overview.component.html',
  styleUrls: ['./ticket-overview.component.css']
})
export class TicketOverviewComponent implements OnInit {
  public tickets: Ticket [] = [];
  public isAuthorized: boolean = false; 
  sortedData: Ticket[];

  constructor(private ticketOverviewService : TicketOverviewService,
    private toast: ToastService) {
      this.sortedData = this.tickets.slice();
    }

 
  ngOnInit(): void {
    this.getTickets(); 
  }

  public getTickets(): void {
    this.ticketOverviewService.getTickets().subscribe(
      (response: Ticket[]) => {
        this.tickets = response;
        this.sortedData = this.tickets.slice();
      },
      (error: HttpErrorResponse) => {
        this.toast.ShowError("New Notification", error.error);
      }
    );
  }

  sortData(sort: Sort) {
    const data = this.tickets.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'summary':
          return compare(a.summary, b.summary, isAsc);
        case 'animal':
          return compare(a.animal.name, b.animal.name, isAsc);
        case 'status':
          return compare(a.status, b.status, isAsc);
        case 'created_on':
          return compare(a.reportDateTime, b.reportDateTime, isAsc);
        case 'created_by':
          return compare(a.reportedByUser.username, b.reportedByUser.username, isAsc);
        default:
          return 0;
      }
    });
  
  function compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }
}

}
