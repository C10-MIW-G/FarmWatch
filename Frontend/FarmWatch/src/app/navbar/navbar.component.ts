import { Component, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from '../security/_services/storage.service';
import { NotifierService } from '../service/notifier.service';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { LoginComponent } from '../component/login/login.component';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  dialogOpen: boolean = false;

  constructor(private storageService: StorageService, 
              private toast: NotifierService, 
              private router: Router,
              private dialog: MatDialog
              ){
              };

  public isLoggedIn(): boolean{
    return this.storageService.isLoggedIn();
  }

  public logout(){
    this.storageService.clean();
    this.router.navigate(['/']);
    this.toast.ShowSucces("New notification", "Logged out succesfully");
  }

  public getRole(): string{
    return this.storageService.getRole();
  }

  public getUserName(): string{
    return this.storageService.getUserName();
  }

  public openDialog(){
    if(!this.dialogOpen){
      this.dialog.open(LoginComponent);
      this.dialogOpen = true;
    } else {
      this.dialog.closeAll();
      this.dialogOpen = false;
    }
  }
}
