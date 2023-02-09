import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CreateTicket } from 'src/app/model/create-ticket';
import { CreateTicketService } from 'src/app/service/create-ticket.service';
import { AnimalOverview } from '../model/animal-overview';
import { StorageService } from '../security/_services/storage.service';
import { AnimalOverviewService } from '../service/animal-overview.service';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.css']
})
export class CreateTicketComponent implements OnInit{

  form: any = {
    title: null,
    description: null,
    animalId: null
  };
  reportUsername= '';
  errorMessage = '';
  animals: AnimalOverview[] = [];

  constructor(private createTicketService: CreateTicketService, private route: ActivatedRoute, 
    private router: Router, private storageService: StorageService, private animalOverviewService: AnimalOverviewService) { }
  ngOnInit(): void {
    this.getAnimals();
  }

  onSubmit(): void {
    const { title, description, animalId} = this.form;
    this.reportUsername = this.storageService.getUser().username;

    this.createTicketService.createTicket(title, description, animalId, this.reportUsername).subscribe({
      next: data => {
        console.log(data);
        this.router.navigate(['/']);
        
      },
      error: err => {
        console.log(err);
        this.errorMessage = err.error;
      }
    });
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
