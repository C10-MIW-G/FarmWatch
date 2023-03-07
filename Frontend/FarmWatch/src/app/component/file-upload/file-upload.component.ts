import { HttpClient, HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, Inject, Input, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { fileuploaddialogdata, fileuploadimage } from 'src/app/model/fileupload-dialog-data';
import { FileUploadService } from 'src/app/service/file-upload.service';
import { ToastService } from 'src/app/service/toast.service';

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
              private uploadService: FileUploadService,
              private toast: ToastService) {}  
    

  onFileSelected(event: any) {
    this.hasFile = false;
    this.uploadReady = false;
    
    this.currentFile = event.target.files[0];

    if (this.currentFile && this.checkFileSize()) {
      this.hasFile = true;
      this.formData = new FormData();

      this.formData.append('imageFile', this.currentFile);
      
      this.uploadFile();
    } 
    var reader = new FileReader();
		reader.readAsDataURL(event.target.files[0]);
		
		reader.onload = (_event) => {
			this.imagePreviewUrl = reader.result; 
		}
  }

  checkFileSize(): Boolean{
    if(this.currentFile!.size/1024 < 2 * 1024){
      console.log("File doenst exceed 2 MB, Image size is: " + Math.round(this.currentFile!.size / 1024) + "KB");
      return true;
    }
    console.log("File exceeded 2 MB, Image size is: " +  Math.round(this.currentFile!.size / 1024) + "KB");
    this.toast.ShowError("Image Upload Failed!", "Image exceeded the 2 MB Max file size.");
    this.currentFile = undefined;
    return false;
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