import { Component, OnInit } from '@angular/core';
import { AnimalDetail } from 'src/app/model/animal-detail';
import {UpdateAnimalService} from '../../service/update-animal.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NotifierService } from 'src/app/service/notifier.service';


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
    private toast: NotifierService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.updateAnimalService.getAnimalDetail(this.id).subscribe(data => {
      this.updateAnimal = data;
    }, error => console.log(error));
  }

  
  onSubmit(){
    this.updateAnimalService.updateAnimal(this.updateAnimal ).subscribe({
      next: data => {
        this.goToAnimalOverview();
        this.toast.ShowSucces("New notification", "Succesfully updated " + this.updateAnimal.name);
      },
      error: err => {
        this.toast.ShowError("New Notification", "Updating " + this.updateAnimal.name + "failed!")
      }
    });
  }

  goToAnimalOverview(){
    this.router.navigate(['/']);
  }
}
