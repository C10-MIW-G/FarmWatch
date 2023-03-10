import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CreateTicket } from 'src/app/model/create-ticket';
import { CreateTicketService } from 'src/app/service/create-ticket.service';
import { DialogService } from 'src/app/service/dialog.service';
import { AnimalOverview } from '../../model/animal-overview';
import { StorageService } from '../../security/_services/storage.service';
import { AnimalOverviewService } from '../../service/animal-overview.service';
import { ToastService } from '../../service/toast.service';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.css']
})
export class CreateTicketComponent implements OnInit{

  form: any = {
    summary: null,
    description: null,
    animalId: -1
  };
  reportUsername = '';
  errorMessage = '';
  animals: AnimalOverview[] = [];
  created = false;
  imageFileName = null;

  constructor(private createTicketService: CreateTicketService, 
    private route: ActivatedRoute, 
    private router: Router, 
    private storageService: StorageService, 
    private animalOverviewService: AnimalOverviewService,
    private toast: ToastService,
    private dialog: DialogService) { }
  ngOnInit(): void {
    this.getAnimals();
  }

  onSubmit(): void {
    const { summary, description, animalId} = this.form;
    this.reportUsername = this.storageService.getUser().username;
    let animalTempId
    if(animalId==-1){
      animalTempId=null;
    }
    else(animalTempId=animalId)

    this.createTicketService.createTicket(summary, description, animalTempId, this.reportUsername, this.imageFileName!).subscribe({
      next: data => {
        this.created = true;
        this.toast.ShowSucces("New Notification", "Succesfully created a ticket");
        setTimeout(() => {
          this.router.navigate(['/']);
      }, 1000); 
      },
      error: err => {
        this.toast.ShowError("New Notification", err.error);
      }
    });
  }

  public getAnimals(): void {
    this.animalOverviewService.getAnimals().subscribe(
      (response: AnimalOverview[]) => {
        this.animals = response;
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

  openImageUploadAction(){
    this.dialog.showUploadFile().subscribe(
      (response) => {
        if(response){
          this.toast.ShowSucces("New Notification", "Image added succesfully")
          this.imageFileName = response;
          console.log("Generated UUID: " + response);
        }
      }
    );
  }
}
