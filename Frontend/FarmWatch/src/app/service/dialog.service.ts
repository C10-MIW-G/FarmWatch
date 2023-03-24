import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { ConfirmationDialogComponent } from '../component/confirmation-dialog/confirmation-dialog.component';
import { NewTicketMessageComponent } from '../component/new-ticket-message/new-ticket-message.component';
import { FileUploadComponent } from '../component/file-upload/file-upload.component';
import { confirmdialogdata } from '../model/confirm-dialog-data';

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  constructor(private dialog: MatDialog) { 
  }

  open(component: any) : Observable<Boolean>{
    this.closeAll();
    return this.dialog.open(component).afterClosed();
  }

  showConfirmDialog(data: confirmdialogdata): Observable<Boolean> {
    this.closeAll();
    return this.dialog.open(ConfirmationDialogComponent, { data }).afterClosed();
  }

  showUploadFile(): Observable<any> {
    return this.dialog.open(FileUploadComponent).afterClosed();
  }

  showNewTicketMessageDialog(privateMessage: boolean): Observable<any> {
    this.closeAll();
    let dialogRef = this.dialog.open(NewTicketMessageComponent);
    dialogRef.componentInstance.setPrivateMessager(privateMessage);
    return dialogRef.afterClosed();
  }

  closeAll(){
    this.dialog.closeAll();
  }
}
