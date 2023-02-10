import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { User } from "src/app/model/user";
import { UserService } from "src/app/service/user.service";
import { ActivatedRoute, Router } from '@angular/router';
import { StorageService } from '../../security/_services/storage.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit{
    public user?: User;
    public id!: number;
    public isAuthorized: boolean = false; 

    constructor(private userDetailService : UserService, 
      private route: ActivatedRoute, 
      private storageService: StorageService) {
        this.route.params.subscribe(params => {
            this.id = params['id'];
        });
    }

    ngOnInit(): void {
      this.getUser(this.id);
      this.isAuthorized = this.storageService.isLoggedIn();
      if(this.storageService.getRole() == 'ADMIN') {
        this.isAuthorized = true; 
      } else {
        this.isAuthorized = false;
      }
    }

    public getUser(id: number): void {
      this.userDetailService.getUserDetail(id).subscribe(
        (response: User) => {
          this.user = response;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
  }
}

