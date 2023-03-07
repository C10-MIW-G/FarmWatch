import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { StorageService } from 'src/app/security/_services/storage.service';

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

  constructor(private dialog: MatDialog, private storageService: StorageService) {}

  onSubmit(){
    this.dialog.closeAll();
  }

  public getRole(): string{
    return this.storageService.getRole();
  }
}
