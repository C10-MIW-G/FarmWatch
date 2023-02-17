import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { confirmdialogdata } from 'src/app/model/confirm-dialog-data';


@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
  styleUrls: ['./confirmation-dialog.component.css']
})
export class ConfirmationDialogComponent {
  confirmButtonText = "Yes";
  cancelButtonText = "No";
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: confirmdialogdata) {}   
  
  hasMessage(): Boolean{
    return this.data.message === '';
  }
}
