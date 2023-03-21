import { Component } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/model/user';
import { ActivatedRoute } from '@angular/router';
import { StorageService } from 'src/app/security/_services/storage.service';
import { HttpErrorResponse} from '@angular/common/http';
import { Location } from '@angular/common';
import { ToastService } from 'src/app/service/toast.service';
import { MatDialog } from '@angular/material/dialog';
import { ForgotPasswordComponent } from '../forgot-password/forgot-password.component';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';
import { AuthService } from 'src/app/security/_services/auth.service';
import { DialogService } from 'src/app/service/dialog.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent {
  public user?: User;
  public id!: number;
  public currentPath: String;
  public userPagePath: String = "details";
  public updatePath: String = "/user/update";
  public isAdmin: boolean = false; 
  public formattedRole: string = "";

  constructor(private userDetailService : UserService, 
    private route: ActivatedRoute, 
    private storageService: StorageService,
    private location: Location,
    private dialog: DialogService,
    private toast: ToastService,
    private auth: AuthService) {
      const pathArray = location.path().split('/');
      this.currentPath = pathArray.pop()!;
      if(this.currentPath === this.userPagePath){
        this.id = storageService.getUser().id;
      } else {
        this.route.params.subscribe(params => {
          this.id = params['id'];
          this.updatePath = this.updatePath + "/" + this.id.toString();
        });
      }  
  }

  ngOnInit(): void {
    this.getUser(this.id);
  }

  public getUser(id: number): void {
    this.userDetailService.getUserDetail(id).subscribe(
      (response: User) => {
        this.user = response;
        if (this.user.role === 'ROLE_USER'){
          this.formattedRole = 'User'
        } else if (this.user.role === 'ROLE_CARETAKER'){
          this.formattedRole = 'Caretaker'
        } else {
          this.formattedRole = 'Administrator'
        }
        if(this.storageService.getRole() == 'ADMIN'){
          this.isAdmin = true; 
        }
      },
      (error: HttpErrorResponse) => {
        if(error.error.message != null){
          this.toast.ShowError("New Notification", error.error.message);
        } else {
          this.toast.ShowError("New Notification", error.error);
        }
      }
    );
  }

  public fullName():String {
    const fullname: string = this.user ? `${this.user.fullName}` : '';
    return fullname;
  }

  passwordReset(){
    this.dialog.closeAll();
    this.dialog.showConfirmDialog({'title': "Reset Password", 'message': "Are you sure you want to reset your password?", 
                                      'confirmButton': "Reset password", 'cancelButton': "Cancel"}).subscribe(
      (response: Boolean) => {
        if(response){
          this.auth.forgotPassword(this.storageService.getUserEmail()).subscribe(
            (confirmResponse: Boolean) =>{
              if(confirmResponse){
                this.toast.ShowSucces("New Notification", "Password reset. Please check your email!")
              } else {
                this.toast.ShowError("New Notification", "Failed to reset password")
              }
            }
          )
        }
      }
    )
  }

}
