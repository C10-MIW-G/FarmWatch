import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { AnimalDetail } from "../../model/animal-detail";
import { AnimalDetailService } from "../../service/animal-detail.service";
import { ActivatedRoute, Router } from '@angular/router';
import { StorageService } from '../../security/_services/storage.service';

@Component({
    selector: 'app-animal',
    templateUrl: './animal-detail.component.html',
    styleUrls: ['./animal-detail.component.css']
  })
export class AnimalDetailComponent implements OnInit {
    public animalDetail?: AnimalDetail;
    public id!: number;
    public isAuthorized: boolean = false; 

    constructor(private animalDetailService : AnimalDetailService, 
      private route: ActivatedRoute, 
      private router: Router, 
      private storageService: StorageService) {
        this.route.params.subscribe(params => {
            this.id = params['id'];
        });
    }
  
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
          },
          (error: HttpErrorResponse) => {
            alert(error.message);
          }
        );
    }

    public onDeleteAnimal(AnimalDetailId: number): void {
      if(window.confirm('Are sure you want to delete '+this.animalDetail?.name+'?')){
      this.animalDetailService.deleteAnimal(AnimalDetailId).subscribe(
        (response: void) => {
            console.log(response);
            this.router.navigate( ['/']);
        },
        (error: HttpErrorResponse) => {
            alert(error.message);
        }
      );
      }
    }

    public onClick(fragment: string): void {
      this.router.navigate( ['/animal/', this.id ], {fragment});
    }

    public toTop(): void {
      window.scroll({top: 0, left: 0})
    }


   



    
}