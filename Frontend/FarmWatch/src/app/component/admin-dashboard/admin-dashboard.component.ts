import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from '../../model/user';
import { AdminDashboardService } from '../../service/admin-dashboard.service';
import { StorageService } from 'src/app/security/_services/storage.service';
import { ToastService } from 'src/app/service/toast.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit{

  public users: User[] = [];


  constructor (private adminDashboardService : AdminDashboardService,
    private storageService: StorageService,
    private toast: ToastService){}
  
  ngOnInit(): void {
    this.adminDashboardService.getUsers().subscribe(
      (response: User[]) => {
        this.users = response;
      },
      (error: HttpErrorResponse) => {
        if(error.error.message != null){
          this.toast.ShowError("New Notification", "Please login again");
        } else {
          this.toast.ShowError("New Notification", error.error);
        }
      }  
    );
  }

  public isCurrentUser(user: User): boolean {
    if(user.id === this.storageService.getUser().id){
      return false;
    }
    return true;
  }
}
