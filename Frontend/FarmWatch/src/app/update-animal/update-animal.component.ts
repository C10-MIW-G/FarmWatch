import { Component, OnInit } from '@angular/core';
import {AnimalOverview} from 'src/app/animal-overview/animal-overview';
import {UpdateAnimalService} from './update-animal.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-update-animal',
  templateUrl: './update-animal.component.html',
  styleUrls: ['./update-animal.component.css']
})
export class UpdateAnimalComponent implements OnInit{
  public updateAnimal!: AnimalOverview;
  public id!: number;

  constructor(private updateAnimalService : UpdateAnimalService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.updateAnimalService.getAnimalDetail(this.id).subscribe(data => {
      this.updateAnimal = data;
    }, error => console.log(error));
  }

  
  onSubmit(){
    this.updateAnimalService.updateAnimal(this.updateAnimal ).subscribe( data =>{
      this.goToAnimalOverview();
    }
    , error => console.log(error));
  }

  goToAnimalOverview(){
    this.router.navigate(['/']);
  }



}
