import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterConfigOptions } from '@angular/router';
import { User } from 'src/app/model/user';
import { StorageService } from 'src/app/security/_services/storage.service';
import { UserService } from 'src/app/service/user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { NotifierService } from 'src/app/service/notifier.service';


@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit{
  public user!: User;
  public id!: number;
  public isAuthorized: boolean = false;
  public canChangePassword: boolean = false;
  public isSuccessful: boolean = false;
  public roles = [
    { name: 'Administrator', value: 'ROLE_ADMIN' },
    { name: 'Caretaker', value: 'ROLE_CARETAKER' },
    { name: 'User', value: 'ROLE_USER' }
  ];
  public canEdit: boolean = false;
  
  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private storageService: StorageService,
    private toast: NotifierService,) {
    this.route.params.subscribe(params => {
        this.id = params['id'];
    });

  }

    ngOnInit(): void {
      this.getUser(this.id)
      this.isAuthorized = this.storageService.isLoggedIn();
      if(this.storageService.getRole() == 'ADMIN') {
        this.isAuthorized = true;
      } else {
        this.isAuthorized = false;
      }
    }
  
    onSubmit(){
    this.userService.updateUser(this.user).subscribe({next: data => {
        setTimeout(() => {
          this.router.navigate(['/admindashboard']);
          this.toast.ShowSucces("New Notification", "Succesfully updated ")
      }, 1000);
      },
      error: err => {
        this.toast.ShowError("New Notification", "Updating " + this.user.username + "failed!")
      }
    });
  }

    private formattedRoles(): void {
      let index = 0;
      if (this.user.role === 'ROLE_ADMIN') {
        index = 0;
      } else if (this.user.role === 'ROLE_USER') {
        index = 2;
      } else if (this.user.role === 'ROLE_CARETAKER'){  
        index = 1;
      } 
      let temp = this.roles[0];
      this.roles[0] = this.roles[index];
      this.roles[index] = temp;
    }

    private allowedToChangePassword(): void{
      if(this.storageService.getRole() === 'USER'){
        this.canChangePassword = true;
      }  
    }

    private getUser(id: number): void {
      this.userService.getUserDetail(id).subscribe(
        (response: User) => {
          this.user = response;
          this.formattedRoles();
          this.allowedToChangePassword();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
  }
}
