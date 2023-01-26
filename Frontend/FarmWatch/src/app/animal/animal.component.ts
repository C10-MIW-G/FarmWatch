import { Component, OnInit } from '@angular/core';
import {Animal} from './animal';
import {AnimalService} from './animal.service';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-animal',
  templateUrl: './animal.component.html',
  styleUrls: ['./animal.component.css']
})
export class AnimalComponent implements OnInit{
  
  public animals: Animal[] = [];

  constructor(private animalService : AnimalService) {}

  ngOnInit(): void {
    this.getAnimals();  
  }


public getAnimals(): void {
  this.animalService.getAnimals().subscribe(
    (response: Animal[]) => {
      this.animals = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
}
}
