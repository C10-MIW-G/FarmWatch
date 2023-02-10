import { Component } from '@angular/core';
import { ActivatedRoute, RouterConfigOptions } from '@angular/router';
import { User } from 'src/app/model/user';
import { StorageService } from 'src/app/security/_services/storage.service';
import { UserService } from 'src/app/service/user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent {
  public user!: User;
  public id!: number;
  public role: string = ""
  public isAuthorized: boolean = false;
  public canChangePassword: boolean = false; 

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router : Router,
    public storageService: StorageService) {
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
      this.userService.updateUser(this.user).subscribe( data =>{
        setTimeout(() => {
          this.router.navigate(['login']);
      }, 1000); 
      }
      , error => console.log(error));
    }

    private formattedRole(): void {
      if(this.user.role === "ROLE_ADMIN"){
        this.role = 'administrator';
        return;
      } else if (this.user.role === "ROLE_USER") {
        this.role = 'user';
        return;
      }  
      this.role = 'caretaker';
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
          this.formattedRole();
          this.allowedToChangePassword();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
  }
}
