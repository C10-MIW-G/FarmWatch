import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { Animal } from "./animal";
import { AnimalService } from "./animal.service";
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-animal',
    templateUrl: './animal.component.html',
    styleUrls: ['./animal.component.css']
  })
export class AnimalComponent implements OnInit{
  
    public animal?: Animal;
    public uuid!: string;

    constructor(private animalService : AnimalService, private route: ActivatedRoute) {
        this.route.params.subscribe(params => {
            this.uuid = params['uuid'];
        });
    }
  
    ngOnInit(): void {
        this.getAnimal(this.uuid);
    }

    public getAnimal(uuid: string): void {
        this.animalService.getAnimal(uuid).subscribe(
          (response: Animal) => {
            this.animal = response;
          },
          (error: HttpErrorResponse) => {
            alert(error.message);
          }
        );
    }
}