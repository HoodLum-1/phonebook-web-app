import { Action } from '@ngrx/store';

import { Contact } from '../shared/models/contact.model';

export const CREATE_CONTACT = 'CREATE_CONTACT';
export const UPDATE_CONTACT = 'UPDATE_CONTACT';
export const DELETE_CONTACT = 'DELETE_CONTACT';

export class CreateContact implements Action {
  readonly type = CREATE_CONTACT;
  
  constructor(public payload: Contact) {}
}

export class UpdateContact implements Action {
  readonly type = UPDATE_CONTACT;

  constructor(public payload: {id: number, value: any}) {}
}

export class DeleteContact implements Action {
  readonly type = DELETE_CONTACT;
  constructor(public payload: number) {}
}

export type ContactActions = 
CreateContact | 
UpdateContact | 
DeleteContact;