import { Component, OnInit } from '@angular/core';

import { Contact } from '../../shared/models/contact.model';
import { ContactService } from '../../services/contact.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-contact-detail',
  templateUrl: './contact-detail.component.html',
  styleUrls: ['./contact-detail.component.css']
})
export class ContactDetailComponent implements OnInit {
  
  id: number;
  contact: Contact;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private contactService: ContactService) { }

  ngOnInit() {
    this.contact = new Contact();

    this.id = this.route.snapshot.params['id'];

    this.contactService.getContact(this.id)
    .subscribe(data => {
      this.contact = data;
    }, error => console.log(error));
  }

  list(){
    this.router.navigate(['contacts'])
  }

}
