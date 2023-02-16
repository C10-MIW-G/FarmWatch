import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AnimalOverview } from 'src/app/model/animal-overview';
import { CaretakerOverview } from 'src/app/model/caretaker-overview';
import { AnimalOverviewService } from 'src/app/service/animal-overview.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-ticket-update',
  templateUrl: './ticket-update.component.html',
  styleUrls: ['./ticket-update.component.css']
})
export class TicketUpdateComponent implements OnInit{

  
  form: any = {
    subject: null,
    description: null,
    status: null,
    assignedTo: null
  };

  animals: AnimalOverview[] = [];
  caretakers: CaretakerOverview[] = [];

  constructor(private animalOverviewService: AnimalOverviewService,
    private userService: UserService){

  }


  ngOnInit(): void {
    this.getAnimals();
    this.getCaretakers();
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

}
