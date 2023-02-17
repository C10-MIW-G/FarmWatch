import { Component } from '@angular/core';
import { AnimalToAdd } from '../../model/add-animal';
import { AddAnimalService } from '../../service/add-animal.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { NotifierService } from 'src/app/service/toast.service';

@Component({
  selector: 'app-add-animal',
  templateUrl: './add-animal.component.html',
  styleUrls: ['./add-animal.component.css',
  ]
})

export class AddAnimalComponent {

  animal: AnimalToAdd 
  
  constructor(private addAnimalService : AddAnimalService, private route: ActivatedRoute, 
    private router: Router, private toast:NotifierService) {
    this.animal = new AnimalToAdd()
  } 

  onSubmit() {
    this.addAnimalService.createAnimal(this.animal).subscribe({
      next: response => {
        this.gotoUserList();
        this.toast.ShowSucces("New notification", "Succesfully added a new animal");
    },
      error: error => {
        this.toast.ShowError("New notification", "Failed to add a new animall");
      }
    });
  }

  

  gotoUserList() {
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    this.router.navigate([''])
  }
}

