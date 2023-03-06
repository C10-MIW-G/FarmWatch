import { HttpClient, HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, Inject, Input, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Observable, Subscription } from 'rxjs';
import { confirmdialogdata } from 'src/app/model/confirm-dialog-data';
import { fileuploaddialogdata, fileuploadimage } from 'src/app/model/fileupload-dialog-data';
import { FileUploadService } from 'src/app/service/file-upload.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})

export class FileUploadComponent {

  confirmButtonText = "Waiting..";
  cancelButtonText = "Cancel";
  generatedUuid = '';
  hasFile = false;
  uploadReady = false;

  currentFile?: File;
  message = '';
  imagePreviewUrl: any;

  formData!: FormData;

  constructor(@Inject(MAT_DIALOG_DATA) public data: fileuploaddialogdata,
              private uploadService: FileUploadService) {}  
    

  onFileSelected(event: any) {
    this.hasFile = false;
    this.uploadReady = false;
    
    this.currentFile = event.target.files[0];

    if (this.currentFile) {

      this.hasFile = true;
      this.formData = new FormData();

      this.formData.append('imageFile', this.currentFile);
      
    } 
    var reader = new FileReader();
		reader.readAsDataURL(event.target.files[0]);
		
		reader.onload = (_event) => {
			this.imagePreviewUrl = reader.result; 
		}
  }

  uploadFile() {
        this.uploadService.upload(this.formData).subscribe({
          next: (response: fileuploaddialogdata) => {
            this.generatedUuid = response.uuid;
            console.log(response.uuid);
            this.uploadReady = true;
          },
          
          error: (err: any) => {
            console.log(err);

            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }

            this.currentFile = undefined;
          }
        });
  }

}