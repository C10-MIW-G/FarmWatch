import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { User } from "src/app/model/user";
import { AnimalDetailService } from "../../service/animal-detail.service";
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent {
  public User?: User;
    public id!: number;
    public isAuthorized: boolean = false; 

    constructor(private animalDetailService : AnimalDetailService, 
      private route: ActivatedRoute, private router: Router) {
        this.route.params.subscribe(params => {
            this.id = params['id'];
        });
    }
}
