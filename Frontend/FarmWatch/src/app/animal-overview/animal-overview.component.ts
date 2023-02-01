import { Component, OnInit } from '@angular/core';
import {AnimalOverview} from './animal-overview';
import {AnimalOverviewService} from './animal-overview.service';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-animal',
  templateUrl: './animal-overview.component.html',
  styleUrls: ['./animal-overview.component.css']
})
export class AnimalOverviewComponent implements OnInit{
  
  public animals: AnimalOverview[] = [];

  constructor(private animalOverviewService : AnimalOverviewService) {}

  ngOnInit(): void {
    this.getAnimals();  
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