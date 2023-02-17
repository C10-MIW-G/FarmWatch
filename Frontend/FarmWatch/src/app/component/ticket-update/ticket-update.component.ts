import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AnimalOverview } from 'src/app/model/animal-overview';
import { CaretakerOverview } from 'src/app/model/caretaker-overview';
import { TicketUpdate } from 'src/app/model/ticket-update';
import { Status } from 'src/app/model/status';
import { AnimalOverviewService } from 'src/app/service/animal-overview.service';
import { UserService } from 'src/app/service/user.service';
import { TicketDetailService } from 'src/app/service/ticket-details.service';
import { ToastService } from 'src/app/service/toast.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-ticket-update',
  templateUrl: './ticket-update.component.html',
  styleUrls: ['./ticket-update.component.css']
})
export class TicketUpdateComponent implements OnInit{

  id!: number;
  ticket!: TicketUpdate;
  animals: AnimalOverview[] = [];
  caretakers: CaretakerOverview[] = [];
  statuses: Status[] = [];
  caretakerNames: string[] = [];
  noValue: String = "Does not apply"

  
  constructor(private animalOverviewService: AnimalOverviewService,
    private userService: UserService,
    private route: ActivatedRoute,
    private ticketDetailService: TicketDetailService,
    private router: Router,
    private toast: ToastService,
    ){
      this.route.params.subscribe(params => {
        this.id = params['id'];
      });
      
  }


  ngOnInit(): void {
    this.getAnimals();
    this.getCaretakers();
    this.getTicket();
    this.getStatuses();
  }

  onSubmit(): void {
    this.caretakerNameToId();
    this.ticketDetailService.updateTicket(this.ticket).subscribe({next: data => {
      setTimeout(() => {
        this.router.navigate(['/ticket/', this.id]);   
        this.toast.ShowSucces("New Notification", "Succesfully updated ")
    }, 500);
    },
    error: err => {
      this.toast.ShowError("New Notification", "Updating failed!")
    }
    });
  }

  private getAnimals(): void {
    this.animalOverviewService.getAnimals().subscribe(
      (response: AnimalOverview[]) => {
        this.putResponseInAnimals(response);  
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  private getCaretakers(): void {
    this.userService.getCaretakers().subscribe(
      (response: CaretakerOverview[]) => {
        this.putResponseInCaretakers(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  private getTicket(): void {
    this.ticketDetailService.getLeanTicket(this.id).subscribe(
      (response: TicketUpdate) => {
        this.ticket = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  private getStatuses(): void {
    this.ticketDetailService.getStatuses().subscribe(
      (response: Status[]) => {
        this.putResponseInStatuses(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  private putResponseInAnimals(response: AnimalOverview[]): void {
    this.animals = response;
  }

  private putResponseInCaretakers(response: CaretakerOverview[]): void {
    this.caretakers = response;
  }

  private putResponseInStatuses(response: Status[]): void {
    this.statuses = response;
  }

  private caretakerNameToId() : void {
    for (const caretaker of this.caretakers) {
      if(this.ticket.assignedToName == caretaker.name){
        this.ticket.assignedTo = caretaker.id;
        return;
      }
    }
    this.ticket.assignedTo = null;
  }
}