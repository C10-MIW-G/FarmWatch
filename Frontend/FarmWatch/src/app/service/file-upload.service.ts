import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AnimalDetail } from '../model/animal-detail';
import { environment } from 'src/environments/environment';
import { fileuploaddialogdata, fileuploadimage } from '../model/fileupload-dialog-data';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public upload(data: FormData): Observable<fileuploaddialogdata> {
    return this.http.post<fileuploaddialogdata>(`${this.apiServerUrl}/images`, data)
  }

}
