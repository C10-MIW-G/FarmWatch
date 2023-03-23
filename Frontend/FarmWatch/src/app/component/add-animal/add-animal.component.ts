import { Component } from '@angular/core';
import { AddAnimalService } from '../../service/add-animal.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { ToastService } from 'src/app/service/toast.service';
import { DialogService } from 'src/app/service/dialog.service';
import { StorageService } from 'src/app/security/_services/storage.service';

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
              private toast:ToastService,
              private dialog: DialogService,
              private storageService: StorageService) {
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

  openImageUploadAction(){
    this.dialog.showUploadFile().subscribe(
      (response) => {
        if(response){
          this.toast.ShowSucces("New Notification", "Image added succesfully")
          this.form.imageFileName = response;
          console.log("Generated UUID: " + response);
        }
      }
    );
}

getAnimalImage(): String{
  console.log(this.form.imageFileName == null);
  if(this.form.imageFileName == null){
    return "http://localhost:8080/images/a59686ae-c324-4b44-9cd7-57c3aa6327df";
      }
  return "http://localhost:8080/images/" + this.form.imageFileName;
}

  gotoUserList() {
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    if(this.storageService.getRole() == 'CARETAKER' || this.storageService.getRole() == 'ADMIN'){
      window.location.href = 'http://localhost:4200/animal';
    } else {
      this.router.navigate(['/']);
    }
  }
}

