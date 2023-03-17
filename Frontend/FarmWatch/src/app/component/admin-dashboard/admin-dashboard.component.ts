import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from '../../model/user';
import { AdminDashboardService } from '../../service/admin-dashboard.service';
import { StorageService } from 'src/app/security/_services/storage.service';
import { ToastService } from 'src/app/service/toast.service';
import { Sort } from '@angular/material/sort';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit{

  public users: User[] = [];
  public sortedData: User[];


  constructor (private adminDashboardService : AdminDashboardService,
    private storageService: StorageService,
    private toast: ToastService){this.sortedData = this.users.slice();}
  
  ngOnInit(): void {
    this.adminDashboardService.getUsers().subscribe(
      (response: User[]) => {
        this.users = response;
        this.sortedData = this.users.slice();
        this.sortData({ active: 'username', direction: 'asc' });
      },
      (error: HttpErrorResponse) => {
        if(error.error.message != null){
          this.toast.ShowError("New Notification", error.error.message);
        } else {
          this.toast.ShowError("New Notification", error.error);
        }
      }  
    );
  }

  sortData(sort: Sort) {
    const data = this.users.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'username':
          return compare(a.username, b.username, isAsc);
        case 'name':
          return compare(a.fullName, b.fullName, isAsc);
        case 'email':
          return compare(a.email, b.email, isAsc);
          case 'role':
          return compare(a.role, b.role, isAsc)
        default:
          return 0;
      }
    });
    function compare(a: number | string, b: number | string, isAsc: boolean) {
      return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
    }
  }

  public isCurrentUser(user: User): boolean {
    if(user.id === this.storageService.getUser().id){
      return false;
    }
    return true;
  }
}
