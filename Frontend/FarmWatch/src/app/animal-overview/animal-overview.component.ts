import { Component, OnInit } from '@angular/core';
import {AnimalOverview} from './animal-overview';
import {AnimalOverviewService} from './animal-overview.service';
import { HttpErrorResponse } from '@angular/common/http';
import { StorageService } from '../_services/storage.service';

@Component({
  selector: 'app-animal',
  templateUrl: './animal-overview.component.html',
  styleUrls: ['./animal-overview.component.css']
})
export class AnimalOverviewComponent implements OnInit{
  public animals: AnimalOverview[] = [];
  public deleteAnimal!: AnimalOverview;
  public isAuthorized: boolean = false; 

  constructor(private animalOverviewService : AnimalOverviewService, private storageService: StorageService) {}

  ngOnInit(): void {
    this.getAnimals(); 
    this.isAuthorized = this.storageService.isLoggedIn();
    if(this.storageService.getRole() == 'USER') {
      this.isAuthorized = true; 
    } else {
      this.isAuthorized = false;
    }
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

  public onDeleteAnimal(AnimalOverviewId: number): void {
    this.animalOverviewService.deleteAnimal(AnimalOverviewId).subscribe(
      (response: void) => {
          console.log(response);
          this.getAnimals();
      },
      (error: HttpErrorResponse) => {
          alert(error.message);
      }
    );
  }
  
}