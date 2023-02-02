import { Component} from '@angular/core';
import { AnimalToAdd } from './add-animal';
import { AddAnimalService } from './add-animal.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import {NgForm} from '@angular/forms';




@Component({
  selector: 'app-add-animal',
  templateUrl: './add-animal.component.html',
  styleUrls: ['./add-animal.component.css',
  ]
})

export class AddAnimalComponent {

  animal: AnimalToAdd 

  constructor(private addAnimalService : AddAnimalService, private route: ActivatedRoute, 
    private router: Router) {
    this.animal = new AnimalToAdd()
  } 

  // newAnimal(): void {
  //   this.animal = {
  //     id: 0,
  //     name: '',
  //   };
  // }
  onSubmit() {
    this.addAnimalService.create(this.animal).subscribe(result => this.gotoUserList());}

  gotoUserList() {
    this.router.navigate(['']);
  }
}

