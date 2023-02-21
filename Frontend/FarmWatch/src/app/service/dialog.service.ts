import { Component, Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { ConfirmationDialogComponent } from '../component/confirmation-dialog/confirmation-dialog.component';
import { confirmdialogdata } from '../model/confirm-dialog-data';

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  constructor(private dialog: MatDialog) { }

  open(component: any){
    this.closeAll();
    this.dialog.open(component)
  }

  showConfirmDialog(data: confirmdialogdata): Observable<Boolean> {
    this.closeAll();
    return this.dialog.open(ConfirmationDialogComponent, { data }).afterClosed() ;
  }

  closeAll(){
    this.dialog.closeAll();
  }
}
