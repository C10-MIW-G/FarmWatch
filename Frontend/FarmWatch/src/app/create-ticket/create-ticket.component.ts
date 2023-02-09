import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CreateTicket } from 'src/app/model/create-ticket';
import { CreateTicketService } from 'src/app/service/create-ticket.service';
import { StorageService } from '../security/_services/storage.service';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.css']
})
export class CreateTicketComponent implements OnInit{

  form: any = {
    title: null,
    description: null,
  };
  reportUsername= '';
  errorMessage = '';

  constructor(private createTicketService: CreateTicketService, private route: ActivatedRoute, 
    private router: Router, private storageService: StorageService) { }
  ngOnInit(): void {}

  onSubmit(): void {
    const { title, description} = this.form;
    this.reportUsername = this.storageService.getUser().username;

    this.createTicketService.createTicket(title, description, this.reportUsername).subscribe({
      next: data => {
        console.log(data);
      },
      error: err => {
        console.log(err);
        this.errorMessage = err.error;
      }
    });
  }
}
