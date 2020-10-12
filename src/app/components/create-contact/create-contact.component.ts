import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { ContactService } from '../../services/contact.service';
import { Contact } from '../../shared/models/contact.model';

@Component({
  selector: 'app-create-contact',
  templateUrl: './create-contact.component.html',
  styleUrls: ['./create-contact.component.css']
})
export class CreateContactComponent implements OnInit {
  @ViewChild('f', {static: true}) createForm: NgForm;

  contact: Contact = new Contact();
  submitted = false;
  types = [{ type: 'Mobile' }, { type: 'Work'}, { type: 'Other' }];

  constructor(
    private contactService: ContactService,
    private router: Router,
    ) {}

  ngOnInit() {
  }

  newContact(): void {
    this.submitted = false;
    this.contact = new Contact();
  }

  save(){
    this.contactService
    .createContact(this.createForm.value).subscribe(data => {
      this.contact = new Contact();
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit(){
    this.submitted = true;
    this.save();
  }

  gotoList(){
    this.router.navigate(['/contacts']);
  }

}
