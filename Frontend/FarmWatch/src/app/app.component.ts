import { Component } from '@angular/core';
import { StorageService } from './security/_services/storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'FarmWatch';
  
  constructor(private storageService: StorageService){};

  public isLoggedIn(): boolean{
    return this.storageService.isLoggedIn();
  }

  public logout(){
    this.storageService.clean();
  }

  public getRole(): string{
    return this.storageService.getRole();
  }
}
