import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterConfigOptions } from '@angular/router';
import { User } from 'src/app/model/user';
import { StorageService } from 'src/app/security/_services/storage.service';
import { UserService } from 'src/app/service/user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { NotifierService } from 'src/app/service/notifier.service';


@Component({
  selector: 'app-user-upate',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit{
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
    this.id = storageService.getUser().id;
      }

    ngOnInit(): void {
      this.getUser(this.id)
      this.isAuthorized = this.storageService.isLoggedIn();
    }
  
    onSubmit(){
      this.userService.updateUser(this.user).subscribe({next: data => {
        setTimeout(() => {
          this.router.navigate(['/update']);
          this.toast.ShowSucces("New Notification", "Succesfully updated " + this.user.username)
      }, 1000);
      },
      error: err => {
        this.toast.ShowError("New Notification", "Updating " + this.user.username + "failed!")
      }
      });
    }

    private allowedToChangePassword(): void{
      if(this.storageService.getRole() === 'USER'){
        this.canChangePassword = true;
      }  
    }

    private getUser(id: number): void {
      this.userService.getUserDetail(this.id).subscribe(
        (response: User) => {
          this.user = response;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
  }
}