import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from './security/_services/storage.service';
import { NotifierService } from './service/toast.service';
import { faCoffee, faUser } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'FarmWatch'; 
}
