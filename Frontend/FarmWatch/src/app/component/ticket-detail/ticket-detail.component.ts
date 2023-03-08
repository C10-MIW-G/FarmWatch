import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Ticket } from 'src/app/model/ticket';
import { TicketMessage } from 'src/app/model/ticket-message';
import { StorageService } from 'src/app/security/_services/storage.service';
import { ToastService } from 'src/app/service/toast.service';
import { TicketDetailService } from 'src/app/service/ticket-details.service';
import { TicketMessageService } from 'src/app/service/ticket-message.service';
import { DialogService } from 'src/app/service/dialog.service';
import { TicketUpdate } from 'src/app/model/ticket-update';
import { NewTicketMessageComponent } from '../new-ticket-message/new-ticket-message.component';
import { tick } from '@angular/core/testing';

@Component({
  selector: 'app-ticket-detail',
  templateUrl: './ticket-detail.component.html',
  styleUrls: ['./ticket-detail.component.css']
})
export class TicketDetailComponent implements OnInit {

  id!: number;
  ticket?: Ticket;
  publicTicketMessages?: TicketMessage[] = []; 
  privateTicketMessages?: TicketMessage[] = [];
  assignToMeTicket?: TicketUpdate;
  currentUser: string;
  showAssignToMeButton: boolean = false;   
  newTicketMessageForm: any = {
    ticketId: null,
    message: null,
    sendByUser: {
      id: null
    },
    privateMessage: null,
  };
  imageUrl = "";

  constructor(
    private ticketDetailService: TicketDetailService,
    private ticketMessageService: TicketMessageService,
    private storageService: StorageService,
    private route: ActivatedRoute,
    private router: Router,
    private toast: ToastService,
    private dialogService: DialogService){
      this.route.params.subscribe(params => {
        this.id = params['id'];
      });
      try {
        this.currentUser = this.storageService.getUserName() as string;
      } catch (error) {
        this.toast.ShowError("New Notification", error);
        this.currentUser = '';
      }
    }
  
  ngOnInit(): void {
    this.getTicket(this.id);
  }

  public getTicket(id: number): void {
    this.ticketDetailService.getTicket(this.id).subscribe({
      next: ticket => {
        this.ticket = ticket;
        if(ticket.assignedToUser == null || ticket.assignedToUser.username != this.currentUser){
          this.showAssignToMeButton = true; 
        } 
        this.imageUrl = "http://localhost:8080/images/" + ticket.imageFileName;
        this.getTicketMessages(this.ticket.ticketMessageIds);
      },
      error: err => {
        this.toast.ShowError("New Notification", err.error.message);
        this.router.navigate(['/ticket']);   
      }
    });
  }

  public getRole(){
    return this.storageService.getRole();
  }

  public getTicketMessages(ticketMessageIds: number[]){
    for(let ticketMessageId of ticketMessageIds){
      this.ticketMessageService.getTicketMessage(ticketMessageId).subscribe({
        next: ticketMessage => this.addTicketMessage(ticketMessage),
        error: error => console.log(error), 
      });
    }
  }

  public addTicketMessage(ticketMessage: TicketMessage): void {
    if(ticketMessage.privateMessage){
      this.privateTicketMessages!.push(ticketMessage);
      this.privateTicketMessages?.sort((a, b) => b.messageLocalDateTime.localeCompare(a.messageLocalDateTime));
    }else{
      this.publicTicketMessages!.push(ticketMessage);
      this.publicTicketMessages?.sort((a, b) => b.messageLocalDateTime.localeCompare(a.messageLocalDateTime));
    }
  }

  sendTicketMessage(){
    this.newTicketMessageForm.sendByUser.id = this.storageService.getUser().id;
    this.newTicketMessageForm.ticketId = this.id;
    this.newTicketMessageForm.message = this.newTicketMessageForm.message.trim();
    this.ticketMessageService.addTicketMessage(this.newTicketMessageForm).subscribe(
      {
        next: (ticketMessage) => {
          this.toast.ShowSucces("New Notification", "Message submitted successfully!")
          this.addTicketMessage(ticketMessage);
          this.newTicketMessageForm.message = '';
        },
        error: err => {
          this.toast.ShowError("New Notification", err.error)
        }
      }
    );
  }

  openNewMessageDialog(privateMessage: boolean){
    this.dialogService.showNewTicketMessageDialog(privateMessage).subscribe({
      next: (result) => {
        if(result != ""){
          this.newTicketMessageForm = result
          this.sendTicketMessage();
        }
      },
      error: err => {
        this.toast.ShowError("New Notification", err.error)
      }
    });
  }

  assignTicketToCurrentUser(ticket?: Ticket): void{
    this.dialogService.showConfirmDialog({title: "Are you sure you want to assign this ticket to yourself?" , message: ""}).subscribe(
      (response: Boolean) => {
        if(ticket && response){
          this.assignToMeTicket = {id: ticket.id, 
                                  animalId: ticket.animal.id,
                                  animalName: ticket.animal.name,
                                  description: ticket.description,
                                  summary: ticket.summary,
                                  status: ticket.status,
                                  assignedTo: null,
                                  assignedToName: this.currentUser};

          this.ticketDetailService.updateTicket(this.assignToMeTicket).subscribe({next: data => { 
                                    if (this.ticket && this.assignToMeTicket?.assignedToName) {
                                      if (this.ticket.assignedToUser) {
                                        this.ticket.assignedToUser.username = this.assignToMeTicket.assignedToName;
                                      } else {
                                        this.ticket.assignedToUser = { id: null, username:this.assignToMeTicket.assignedToName };
                                      }
                                      this.showAssignToMeButton = false;
                                      this.toast.ShowSucces("New Notification", "Ticket has been assigned to " + this.currentUser)   
                                    } else {
                                    setTimeout(() => {
                                      this.toast.ShowError("New Notification", "Something went wrong")
                                      this.router.navigate(['/ticket/', this.id]);
                                      }, 1000);
                                    }
                                  },
                                  error: err => {
                                    this.toast.ShowError("New Notification", err.error)
                                  }
                                  });
                                }                        
      }
    )
  }
  
}
