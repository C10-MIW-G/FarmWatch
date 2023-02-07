import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { AnimalDetail } from "../../model/animal-detail";
import { AnimalDetailService } from "../../service/animal-detail.service";
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-animal',
    templateUrl: './animal-detail.component.html',
    styleUrls: ['./animal-detail.component.css']
  })
export class AnimalDetailComponent implements OnInit{
  
    public animalDetail?: AnimalDetail;
    public id!: number;

    constructor(private animalDetailService : AnimalDetailService, private route: ActivatedRoute) {
        this.route.params.subscribe(params => {
            this.id = params['id'];
        });
    }
  
    ngOnInit(): void {
        this.getAnimalDetail(this.id);
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
}