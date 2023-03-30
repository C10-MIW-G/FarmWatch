import { Component, OnInit } from '@angular/core';
import {AnimalOverview} from '../../model/animal-overview';
import {AnimalOverviewService} from '../../service/animal-overview.service';
import { HttpErrorResponse } from '@angular/common/http';
import { StorageService } from '../../security/_services/storage.service';
import { ToastService } from 'src/app/service/toast.service';
import { Sort } from '@angular/material/sort';
import { DialogService } from 'src/app/service/dialog.service';
import { AddAnimalComponent } from '../add-animal/add-animal.component';

@Component({
  selector: 'app-animal',
  templateUrl: './animal-overview.component.html',
  styleUrls: ['./animal-overview.component.css']
})
export class AnimalOverviewComponent implements OnInit{
  public animals: AnimalOverview[] = [];
  sortedData: AnimalOverview[];
  public deleteAnimal!: AnimalOverview;
  public isAuthorized: boolean = false; 

  constructor(private animalOverviewService : AnimalOverviewService, 
    private storageService: StorageService,
    private toast: ToastService,
    private dialog: DialogService) 
    {this.sortedData = this.animals.slice();}

  ngOnInit(): void {
    this.getAnimals(); 
    this.isAuthorized = this.storageService.isLoggedIn();
    if(this.storageService.getRole() == 'ADMIN') {
      this.isAuthorized = true; 
    } else {
      this.isAuthorized = false;
    }
  }

  public getAnimals(): void {
    this.animalOverviewService.getAnimals().subscribe(
      (response: AnimalOverview[]) => {
        this.animals = response;
        this.sortData({ active: 'name', direction: 'asc' });
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

  public isLoggedIn(): boolean{
    return this.storageService.isLoggedIn();
  }

  public getRole(): string{
    return this.storageService.getRole();
  }

  sortData(sort: Sort) {
    const data = this.animals.slice();
    if (!sort.active || sort.direction === '') {
      this.animals = data;
      this.sortData({ active: 'name', direction: 'asc' });
      return;
    }

    this.animals = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name':
          return compare(a.name, b.name, isAsc);
        case 'animal':
          return compare(a.commonName, b.commonName, isAsc);
        case 'reports':
          return compare(a.ticketAmount, b.ticketAmount, !isAsc)
        default:
          return 0;
      }
    });
    function compare(a: number | string, b: number | string, isAsc: boolean) {
      return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
    }
  }
  
  openAddAnimalDialog(){
    this.dialog.open(AddAnimalComponent);
  }

}