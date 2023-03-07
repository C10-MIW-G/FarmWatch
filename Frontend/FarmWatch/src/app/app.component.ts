import { Component } from '@angular/core';
import { Router } from '@angular/router';
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
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.eventBusSub = this.eventBusService.on('logout', () => {
      this.logout();
    });
  }

  logout(): void {
      this.storageService.clean();
      this.router.navigate(['/']);
  }
}
