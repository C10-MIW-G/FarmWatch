import { Component } from '@angular/core';
import { AddAnimalService } from '../../service/add-animal.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { ToastService } from 'src/app/service/toast.service';

@Component({
  selector: 'app-add-animal',
  templateUrl: './add-animal.component.html',
  styleUrls: ['./add-animal.component.css',
  ]
})

export class AddAnimalComponent {

  form: any = {
    name: null,
    commonName: null,
    species: null,
    description: null,
    dateOfBirth: null,
    imageFileName: null,
  }
  
  constructor(private addAnimalService : AddAnimalService,
              private router: Router, 
              private toast:ToastService) {
  } 

  onSubmit() {
    const {name, commonName, species, description, dateOfBirth, imageFileName } = this.form;
    this.addAnimalService.createAnimal(name, commonName, species, description, dateOfBirth, imageFileName).subscribe({
      next: response => {
        this.gotoUserList();
        this.toast.ShowSucces("New notification", "Succesfully added a new animal");
    },
      error: error => {
        if(error.error.message != null){
          this.toast.ShowError("New Notification", error.error.message);
        } else {
          this.toast.ShowError("New Notification", error.error);
        }
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

