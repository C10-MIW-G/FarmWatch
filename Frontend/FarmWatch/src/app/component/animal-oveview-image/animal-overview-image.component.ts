import { Component, OnInit } from '@angular/core';
import {AnimalOverview} from '../../model/animal-overview';
import {AnimalOverviewService} from '../../service/animal-overview.service';
import { HttpErrorResponse } from '@angular/common/http';
import { StorageService } from '../../security/_services/storage.service';
import { ToastService } from 'src/app/service/toast.service';


@Component({
  selector: 'app-animal-overview-image',
  templateUrl: './animal-overview-image.component.html',
  styleUrls: ['./animal-overview-image.component.css']
})

export class AnimalOverviewImageComponent implements OnInit{
  public animals: AnimalOverview[] = [];
  public deleteAnimal!: AnimalOverview;
  public animalPrepped: AnimalOverview[][] = [[]];
  private numSubArrays: number = 0;
  private picturePerCell: number = 3;


  constructor(private animalOverviewService : AnimalOverviewService, 
    private storageService: StorageService,
    private toast: ToastService) {}

  ngOnInit(): void {
    this.getAnimals(); 
  }


  public getAnimals(): void {
    this.animalOverviewService.getAnimals().subscribe(
      (response: AnimalOverview[]) => {
        this.animals = response;
        this.setImageUrl();
        this.calculateCarouselCells();
        this.preppAnimals();
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

  public amountOfCarouselCells() {
    const array = new Array(this.numSubArrays);
    for (let i = 0; i < this.numSubArrays; i++) {
      array[i] = i;
    }
    return array;
  }

  private calculateCarouselCells(): void{
    this.numSubArrays = Math.ceil(this.animals.length / this.picturePerCell);
  }
  
  private setImageUrl() : void {
    const url: String = "http://localhost:8080/images/"

    this.animals.forEach(animal => {
      animal.imagePath = url + animal.imagePath
    });
  }
  
  private preppAnimals() : void{
    let pictureCellCount: number = 0;
    let indexArray: number = 0; 
    this.animalPrepped = new Array(this.numSubArrays).fill(null).map(() => []);
    for (let i = 0; i < this.animals.length; i++) {
      this.animalPrepped[indexArray][pictureCellCount] = {
        id: this.animals[i].id,
        name: this.animals[i].name,
        commonName: this.animals[i].commonName,
        imagePath: this.animals[i].imagePath,
        ticketAmount: 0
        };
        pictureCellCount++;
        if(pictureCellCount === 3){
          pictureCellCount = 0;
          indexArray++;
        }
    }    
  }
}