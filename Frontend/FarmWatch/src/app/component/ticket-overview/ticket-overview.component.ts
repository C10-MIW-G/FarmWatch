import { Component, OnInit } from '@angular/core';
import { Ticket } from 'src/app/model/ticket';
import { TicketOverviewService } from 'src/app/service/ticket-overview.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ToastService } from 'src/app/service/toast.service';
import { Sort } from '@angular/material/sort';
import { StorageService } from 'src/app/security/_services/storage.service';

@Component({
  selector: 'app-ticket-overview',
  templateUrl: './ticket-overview.component.html',
  styleUrls: ['./ticket-overview.component.css']
})
export class TicketOverviewComponent implements OnInit {
  public tickets: Ticket [] = [];
  public isAuthorized: boolean = false; 
  sortedData: Ticket[];
  filterValue: string;
  loggedInUsername: string;
  showFilterCheckBox: boolean = false;
  roleUser: string; 

  constructor(private ticketOverviewService : TicketOverviewService,
    private toast: ToastService,
    private storageService: StorageService,
    ) {
      this.sortedData = this.tickets.slice();
      this.filterValue = '';
      this.canCheckBoxBeShown();
      this.roleUser = this.storageService.getRole()
      try {
        this.loggedInUsername = this.storageService.getUserName() as string;
      } catch (error) {
        this.toast.ShowError("New Notification", error);
        this.loggedInUsername = '';
      }
    }

 
  ngOnInit(): void {
    this.getTickets();  
  }

  public getTickets(): void {
    this.ticketOverviewService.getTickets().subscribe(
      (response: Ticket[]) => {
        this.tickets = response;
        this.sortedData = this.tickets.slice();
        this.sortData({ active: 'status', direction: 'desc' });
        
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
    const data = this.tickets.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'summary':
          return compare(a.summary, b.summary, isAsc);
        case 'animal':
          return compare(a.animal.name, b.animal.name, isAsc);
        case 'status':
          const statusComparison = a.status.localeCompare(b.status);
          if (statusComparison !== 0) {
            return (isAsc ? statusComparison : -statusComparison);
          } else {
            return (isAsc ? compare(a.reportDateTime, b.reportDateTime, !isAsc) : compare(b.reportDateTime, a.reportDateTime, !isAsc));
          }
        case 'created_on':
          return compare(a.reportDateTime, b.reportDateTime, isAsc);
        case 'created_by':
          return compare(a.reportedByUser.username, b.reportedByUser.username, isAsc);
        default:
          return 0;
      }
    });
  
    function compare(a: number | string, b: number | string, isAsc: boolean) {
      return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
    }

  }
  private canCheckBoxBeShown(): void{
    if(this.storageService.getRole() == 'USER'){
      this.showFilterCheckBox = false;
    } else {
      this.showFilterCheckBox = true;
    }
  }

  public checkBoxChanged(event: any): void {
    if (event.checked) {
      this.filterValue = this.loggedInUsername;
    } else {
      this.filterValue = '';
    }
  }



}
