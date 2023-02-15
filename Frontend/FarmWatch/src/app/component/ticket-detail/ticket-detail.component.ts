import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Ticket } from 'src/app/model/ticket';
import { TicketMessage } from 'src/app/model/ticket-message';
import { StorageService } from 'src/app/security/_services/storage.service';
import { NotifierService } from 'src/app/service/notifier.service';
import { TicketDetailService } from 'src/app/service/ticket-details.service';
import { TicketMessageService } from 'src/app/service/ticket-message.service';

@Component({
  selector: 'app-ticket-detail',
  templateUrl: './ticket-detail.component.html',
  styleUrls: ['./ticket-detail.component.css']
})
export class TicketDetailComponent implements OnInit {

  id!: number;
  ticket?: Ticket;
  ticketMessages?: TicketMessage[] = [];  
  newTicketMessageForm: any = {
    ticketId: null,
    message: null,
    sendByUser: {
      id: null
    }
  };

  constructor(private ticketDetailService: TicketDetailService,
    private ticketMessageService: TicketMessageService,
    private storageService: StorageService,
    private route: ActivatedRoute,
    private toast: NotifierService){
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
        this.getTicketMessages(this.ticket.ticketMessageIds);
      },
      error: error => console.log(error)
    });
  }

  public getTicketMessages(ticketMessageIds: number[]){
    this.ticketMessages = [];
    for(let ticketMessageId of ticketMessageIds){
      this.ticketMessageService.getTicketMessage(ticketMessageId).subscribe({
        next: ticketMessage => this.addTicketMessage(ticketMessage),
        error: error => console.log(error)
      });
    }
  }

  public addTicketMessage(ticketMessage: TicketMessage): void {
    this.ticketMessages!.push(ticketMessage);
    this.ticketMessages?.sort((a, b) => b.messageLocalDateTime.localeCompare(a.messageLocalDateTime));
  }

  onSubmit(){
    this.newTicketMessageForm.sendByUser.id = this.storageService.getUser().id;
    this.newTicketMessageForm.ticketId = this.id;
    this.ticketMessageService.addTicketMessage(this.newTicketMessageForm).subscribe(
      {
        next: (ticketMessage) => {
          this.toast.ShowSucces("New Notification", "Message submitted successfully!")
          this.addTicketMessage(ticketMessage);
          this.newTicketMessageForm.message = '';
        },
        error: err => {
          this.toast.ShowError("New Notification", "Adding new message failed!")
        }
      }
    );
  }
}
