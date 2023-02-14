import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { StorageService } from '../security/_services/storage.service';
import { NotifierService } from '../service/notifier.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  faUser = faUser;

  constructor(private storageService: StorageService, 
              private toast: NotifierService, 
              private router: Router){
              };

  public isLoggedIn(): boolean{
    return this.storageService.isLoggedIn();
  }

  public logout(){
    this.storageService.clean();
    this.router.navigate(['login']);
    this.toast.ShowSucces("New notification", "Logged out succesfully");
  }

  public getRole(): string{
    return this.storageService.getRole();
  }

  public getUsername(): string{
    let currentUser = this.storageService.getUser();
    return currentUser.name;
  }
}
