import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { Store } from '@ngrx/store';

import { Observable } from "rxjs";
import { ContactService} from '../../services/contact.service';
import { Contact } from '../../shared/models/contact.model';
import * as ContactActions from '../../ngRx-Store/contact.actions'

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css']
})
export class ContactListComponent implements OnInit {
  contacts: Observable<Contact[]>
  contactNgStore: Observable<{contactNgStore: Contact[]}>;

  displayedColumnHead = ['name', 'email', 'number', 'type', 'actions'];
  dataContacts: MatTableDataSource<Contact>;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(
    private contactService: ContactService,
    private router: Router,
    private store: Store<{contact: {contactNgStore: Contact[]}}>
  ) {}

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase(); 
    this.dataContacts.filter = filterValue;
  }

  ngOnInit() {
    this.contactNgStore = this.store.select('contact');
    this.dataContacts = new MatTableDataSource();
    this.getContacts();
    this.dataContacts.paginator = this.paginator;
    this.dataContacts.sort = this.sort;
  }

  reloadData(){
    this.dataContacts = new MatTableDataSource();
    this.getContacts();
  }

  getContacts() {
    this.contactNgStore = this.store.select('contact');
    this.contactService.getContactList().subscribe((data) => {
      this.contacts = data;
      this.dataContacts.data = data;
      return data;
    });
  }

  deleteContact(id: number) {
    this.contactService.deleteContact(id)
    .subscribe(data => {
      console.log(data);
      this.reloadData();
    }, error => console.log(error));
    // this.store.dispatch(new ContactActions.DELETE_CONTACT);
  }

  contactDetail(id: number) {
    this.router.navigate(['detail', id]);
  }

  updateContact(id: number) {
    this.router.navigate(['update', id])
  }

}
