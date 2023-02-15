import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CreateTicket } from 'src/app/model/create-ticket';
import { CreateTicketService } from 'src/app/service/create-ticket.service';
import { AnimalOverview } from '../../model/animal-overview';
import { StorageService } from '../../security/_services/storage.service';
import { AnimalOverviewService } from '../../service/animal-overview.service';
import { NotifierService } from '../../service/notifier.service';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.css']
})
export class CreateTicketComponent implements OnInit{

  form: any = {
    subject: null,
    description: null,
    animalId: null
  };
  reportUsername= '';
  errorMessage = '';
  animals: AnimalOverview[] = [];
  created = false;

  constructor(private createTicketService: CreateTicketService, private route: ActivatedRoute, 
    private router: Router, private storageService: StorageService, private animalOverviewService: AnimalOverviewService,
    private toast: NotifierService) { }
  ngOnInit(): void {
    this.getAnimals();
  }

  onSubmit(): void {
    const { subject, description, animalId} = this.form;
    this.reportUsername = this.storageService.getUser().username;

    this.createTicketService.createTicket(subject, description, animalId, this.reportUsername).subscribe({
      next: data => {
        this.created = true;
        this.toast.ShowSucces("New Notification", "Succesfully created a ticket");
        setTimeout(() => {
          this.router.navigate(['/']);
      }, 1000); 
      },
      error: err => {
        this.toast.ShowError("New Notification", "Creating a new ticket failed");
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
