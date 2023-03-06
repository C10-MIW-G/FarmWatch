import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit, ViewChild, TemplateRef } from "@angular/core";
import { AnimalDetail } from "../../model/animal-detail";
import { AnimalDetailService } from "../../service/animal-detail.service";
import { ActivatedRoute, Router } from '@angular/router';
import { StorageService } from '../../security/_services/storage.service';
import { ToastService } from "src/app/service/toast.service";
import { DialogService } from "src/app/service/dialog.service";
import { FileUploadService } from "src/app/service/file-upload.service";
import { fileuploaddialogdata } from "src/app/model/fileupload-dialog-data";

@Component({
    selector: 'app-animal',
    templateUrl: './animal-detail.component.html',
    styleUrls: ['./animal-detail.component.css']
  })
export class AnimalDetailComponent implements OnInit {
    public animalDetail?: AnimalDetail;
    public id!: number;
    public isAuthorized: boolean = false; 
    public imageUrl: string = "localhost:8080/image/"

    confirmationMessage = "Are you sure you want to delete ";

    constructor(private animalDetailService : AnimalDetailService, 
      private route: ActivatedRoute, 
      private router: Router, 
      private storageService: StorageService,
      private toast: ToastService,
      private dialog: DialogService,
      private fileupload: FileUploadService) {
        this.route.params.subscribe(params => {
            this.id = params['id'];
        });
    }

    @ViewChild('deleteDialog', { static: true }) deleteDialog: TemplateRef<any> | undefined;
  
    ngOnInit(): void {
        this.getAnimalDetail(this.id);
        this.isAuthorized = this.storageService.isLoggedIn();
        if(this.storageService.getRole() == 'ADMIN') {
          this.isAuthorized = true; 
        } else {
          this.isAuthorized = false;
        }
      }

    public getAnimalDetail(id: number): void {
        this.animalDetailService.getAnimalDetail(id).subscribe(
          (response: AnimalDetail) => {
            this.animalDetail = response;
            this.imageUrl = "http://localhost:8080/images/" + this.animalDetail.imageFileName;
          },
          (error: HttpErrorResponse) => {
            alert(error.message);
          }
        );
    }

    public onDeleteAnimal(AnimalDetailId: number): void {
      this.animalDetailService.deleteAnimal(AnimalDetailId).subscribe(
        (response: void) => {
            console.log(response);
            this.router.navigate(['/']);
            this.toast.ShowSucces("New Notification", this.animalDetail?.name + " deleted succesfully")
        },
        (error: HttpErrorResponse) => {
            this.toast.ShowError("New Notification", this.animalDetail?.name + " could not be deleted")
        }
      );
      this.closeDialog();
    }

    closeDialog() {
      this.closeDialog();
  }

  public isLoggedIn(): boolean{
    return this.storageService.isLoggedIn();
  }

  public getRole(): string{
    return this.storageService.getRole();
  }

  confirmDeleteAction(AnimalDetailId: number): void {
    this.dialog.showConfirmDialog({title: "Are you sure you want to delete " + this.animalDetail?.name + "?", message: ""}).subscribe(
      (response: Boolean) => {
        console.log(response);
        if (response) {
          this.animalDetailService.deleteAnimal(AnimalDetailId).subscribe(
            (response: void) => {
                this.router.navigate(['/']);
                this.toast.ShowSucces("New Notification", this.animalDetail?.name + " deleted succesfully")
            },
            (error: HttpErrorResponse) => {
                this.toast.ShowError("New Notification", this.animalDetail?.name + " could not be deleted")
            }
          );
        }
      },
      (error: HttpErrorResponse) => {
        this.toast.ShowError("New Notification", this.animalDetail?.name + " could not be deleted");
      }
    );
  }
}