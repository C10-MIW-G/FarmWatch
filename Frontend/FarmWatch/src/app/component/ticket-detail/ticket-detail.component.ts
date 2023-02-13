import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Ticket } from 'src/app/model/ticket';
import { TicketMessage } from 'src/app/model/ticket-message';
import { TicketDetailService } from 'src/app/service/ticket-details.service';
import { TicketMessageService } from 'src/app/service/ticket-message.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-ticket-detail',
  templateUrl: './ticket-detail.component.html',
  styleUrls: ['./ticket-detail.component.css']
})
export class TicketDetailComponent implements OnInit {

  id!: number;
  ticket?: Ticket;
  ticketMessages?: TicketMessage[] = [];
  reportedByUsername?: string;

  constructor(private ticketDetailService: TicketDetailService,
    private ticketMessageService: TicketMessageService,
    private userService: UserService,
    private route: ActivatedRoute){
    this.route.params.subscribe(params => {
      this.id = params['id'];
  });
  }
  
  ngOnInit(): void {
    this.getTicket(this.id);
  }

  public getTicket(id: number): void {
    this.ticketDetailService.getTicket(this.id).subscribe({
      next: ticket => {
        this.ticket = ticket,
        this.getReportedByUsername(this.ticket.reportedByUserId);
        this.getTicketMessages(this.ticket.ticketMessageIds);
      },
      error: error => console.log(error)
    });
  }

  public getTicketMessages(ticketMessageIds: number[]){
    for(let ticketMessageId of ticketMessageIds){
      this.ticketMessageService.getTicketMessage(ticketMessageId).subscribe({
        next: ticketMessage => this.ticketMessages!.push(ticketMessage),
        error: error => console.log(error)
      });
    }
  }

  public getReportedByUsername(reportedByUserId: number){
    this.userService.getUserDetail(reportedByUserId).subscribe({
      next: user => this.reportedByUsername = user.username,
      error: error => console.log(error)
    });
  }
}
