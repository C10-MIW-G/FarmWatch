import { Component, OnInit } from '@angular/core';
import { Ticket } from 'src/app/model/ticket';
import { TicketOverviewService } from 'src/app/service/ticket-overview.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-ticket-overview',
  templateUrl: './ticket-overview.component.html',
  styleUrls: ['./ticket-overview.component.css']
})
export class TicketOverviewComponent implements OnInit{
  public tickets: Ticket [] = [];
  public isAuthorized: boolean = false; 

  constructor(private ticketOverviewService : TicketOverviewService) {}

  ngOnInit(): void {
    this.getTickets(); 
  }

  public getTickets(): void {
    this.ticketOverviewService.getTickets().subscribe(
      (response: Ticket[]) => {
        this.tickets = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
