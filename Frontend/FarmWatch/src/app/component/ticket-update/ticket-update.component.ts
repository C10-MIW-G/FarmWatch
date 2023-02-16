import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AnimalOverview } from 'src/app/model/animal-overview';
import { AnimalOverviewService } from 'src/app/service/animal-overview.service';

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

  constructor(private animalOverviewService: AnimalOverviewService){

  }


  ngOnInit(): void {
   this.getAnimals();
   
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

}
