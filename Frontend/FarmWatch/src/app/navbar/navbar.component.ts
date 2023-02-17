import { Component, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from '../security/_services/storage.service';
import { ToastService } from '../service/toast.service';
import { LoginComponent } from '../component/login/login.component';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { DialogService } from '../service/dialog.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  dialogOpen: boolean = false;
  faUser = faUser;

  constructor(private storageService: StorageService, 
              private toast: ToastService, 
              private router: Router,
              private dialog: DialogService
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
      this.dialog.open(LoginComponent);
  }
}
