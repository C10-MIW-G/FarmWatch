import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AnimalOverview } from 'src/app/model/animal-overview';
import { CaretakerOverview } from 'src/app/model/caretaker-overview';
import { TicketUpdate } from 'src/app/model/ticket-update';
import { AnimalOverviewService } from 'src/app/service/animal-overview.service';
import { UserService } from 'src/app/service/user.service';
import { TicketDetailService } from 'src/app/service/ticket-details.service';



@Component({
  selector: 'app-ticket-update',
  templateUrl: './ticket-update.component.html',
  styleUrls: ['./ticket-update.component.css']
})
export class TicketUpdateComponent implements OnInit{

  id!: number;
  ticket?: TicketUpdate;
  animals: AnimalOverview[] = [];
  caretakers: CaretakerOverview[] = [];

  form: any = {
    subject: null,
    description: null,
    status: null,
    assignedTo: null
  };

  

  constructor(private animalOverviewService: AnimalOverviewService,
    private userService: UserService,
    private route: ActivatedRoute,
    private ticketDetailService: TicketDetailService){
      this.route.params.subscribe(params => {
        this.id = params['id'];
      });
  }


  ngOnInit(): void {
    this.getAnimals();
    this.getCaretakers();
    this.getTicket();
  }

  onSubmit(): void {
    
  }

  public getAnimals(): void {
    this.animalOverviewService.getAnimals().subscribe(
      (response: AnimalOverview[]) => {
        this.animals = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getCaretakers(): void {
    this.userService.getCaretakers().subscribe(
      (response: CaretakerOverview[]) => {
        this.caretakers = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getTicket(): void {
    this.ticketDetailService.getLeanTicket(this.id).subscribe(
      (response: TicketUpdate) => {
        this.ticket = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
