import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-new-ticket-message',
  templateUrl: './new-ticket-message.component.html',
  styleUrls: ['./new-ticket-message.component.css']
})
export class NewTicketMessageComponent {
  newTicketMessageForm: any = {
    ticketId: null,
    message: null,
    sendByUser: {
      id: null
    },
    privateMessage: null
  };

  constructor(private dialog: MatDialog) {}

  onSubmit(){
    this.dialog.closeAll();
  }

  onExit(){
    this.dialog.closeAll();
  }
}
