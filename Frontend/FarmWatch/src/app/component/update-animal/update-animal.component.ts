import { Component, OnInit } from '@angular/core';
import { AnimalDetail } from 'src/app/model/animal-detail';
import {UpdateAnimalService} from '../../service/update-animal.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastService } from 'src/app/service/toast.service';
import { FileUploadService } from 'src/app/service/file-upload.service';
import { DialogService } from 'src/app/service/dialog.service';
import { HttpErrorResponse } from '@angular/common/http';
import { fileuploaddialogdata } from 'src/app/model/fileupload-dialog-data';
import { StorageService } from 'src/app/security/_services/storage.service';


@Component({
  selector: 'app-update-animal',
  templateUrl: './update-animal.component.html',
  styleUrls: ['./update-animal.component.css']
})
export class UpdateAnimalComponent implements OnInit{
  public updateAnimal!: AnimalDetail;
  public id!: number;

  constructor(private updateAnimalService : UpdateAnimalService,
    private route: ActivatedRoute,
    private router: Router,
    private toast: ToastService,
    private dialog: DialogService,
    private storageService: StorageService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.updateAnimalService.getAnimalDetail(this.id).subscribe(data => {
      this.updateAnimal = data;
    }, error => this.toast.ShowError("New Notification", error.error));
  }

  public isLoggedIn(): boolean{
    return this.storageService.isLoggedIn();
  }

  public getRole(): string{
    return this.storageService.getRole();
  }

  confirmDeleteAction(AnimalDetailId: number): void {
    this.dialog.showConfirmDialog({title: "Are you sure you want to delete " + this.updateAnimal?.name + "?", message: ""}).subscribe(
      (response: Boolean) => {
        console.log(response);
        if (response) {
          this.updateAnimalService.deleteAnimal(AnimalDetailId).subscribe(
            (response: void) => {
                this.router.navigate(['/']);
                this.toast.ShowSucces("New Notification", this.updateAnimal?.name + " deleted succesfully")
            },
            (error: HttpErrorResponse) => {
                this.toast.ShowError("New Notification", this.updateAnimal?.name + " could not be deleted")
            }
          );
        }
      },
      (error: HttpErrorResponse) => {
        this.toast.ShowError("New Notification", this.updateAnimal?.name + " could not be deleted");
      }
    );
  }

  openImageUploadAction(){
    this.dialog.showUploadFile().subscribe(
      (response) => {
        if(response){
          this.toast.ShowSucces("New Notification", "Image added succesfully")
          this.updateAnimal.imageFileName = response;
          console.log("Generated UUID: " + response);
        }
      }
    );
}

  
  onSubmit(){
    this.updateAnimalService.updateAnimal(this.updateAnimal).subscribe({
      next: data => {
        this.goToAnimalOverview();
        this.toast.ShowSucces("New notification", "Succesfully updated " + this.updateAnimal.name);
      },
      error: err => {
        this.toast.ShowError("New Notification", err.error);
      }
    });
  }

  goToAnimalOverview(){
    this.router.navigate(['/']);
  }
}
