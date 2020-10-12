import { async, inject, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ContactService } from './contact.service';

describe('ContactService', () => {
  let contactService: ContactService;
  let httpMock: HttpTestingController;

  beforeEach(() => 
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
      ],
      providers: [
        ContactService
      ],
    }));

    contactService = TestBed.get(ContactService);
    httpMock = TestBed.get(HttpTestingController);

  it('should be created', () => {
    const service: ContactService = TestBed.get(ContactService);
    expect(service).toBeTruthy();
    
    it(`should fetch posts as an Observable`, async(inject([HttpTestingController, ContactService],
      (httpClient: HttpTestingController, contactService: ContactService) => {
        
        const contactItem = [
          {
            "id": 2,
            "name": "Stringz",
            "email": "stringz@happy.co.za",
            "number": "0797159358",
            "type": "Mobile",
            "createdDate": "2020-10-05 21:59:10"
          },
          {
            "id": 5,
            "name": "Happy",
            "email": "happy@stringz.co.za",
            "number": "0797176458",
            "type": "Mobile",
            "createdDate": "2020-10-05 22:03:45"
          },
        ];

        contactService.getContactList()
          .subscribe((contacts: any) => {
            expect(contacts.length).toBe(2);
          });

        let req = httpMock.expectOne('http://localhost:5000/contacts/v1');
        expect(req.request.method).toBe("GET");
  
        req.flush(contactItem);
        httpMock.verify();
      })
    ));
  })
});