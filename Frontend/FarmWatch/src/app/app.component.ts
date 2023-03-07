import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { StorageService } from './security/_services/storage.service';
import { EventBusService } from './security/_shared/event-bus.service';
import { ToastService } from './service/toast.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'FarmWatch'; 
  eventBusSub?: Subscription;

  constructor(
    private storageService: StorageService,
    private eventBusService: EventBusService,
  ) {}

  ngOnInit(): void {
    this.eventBusSub = this.eventBusService.on('logout', () => {
      this.logout();
    });
  }

  logout(): void {
      this.storageService.clean();
  }
}
