import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from './security/_services/storage.service';
import { NotifierService } from './service/notifier.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'FarmWatch';
  
  constructor(private storageService: StorageService, private toast: NotifierService, private router: Router){};

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
}
