import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr'

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(private toast: ToastrService) { }

  ShowSucces(title : any, message : any){
    this.toast.success(message, title);
  }

  ShowWarning(title : any, message : any){
    this.toast.warning(message, title);
  }

  ShowError(title : any, message : any){
    this.toast.error(message, title);
  }

  ShowInfo(title : any, message : any){
    this.toast.info(message, title);
  }
}
