import * as ContactActions from './contact.actions';

import { Contact } from '../shared/models/contact.model';

const initialState = {
  contacts: [
    new Contact()
    // new Contact(0,'Willson','willson@will.co.za', '0767156600', 'Mobile'),
  ]
};

export function contactReducer(state = initialState, action: ContactActions.ContactActions) {
  switch (action.type) {
    case ContactActions.CREATE_CONTACT:
      return {
        ...state,
        contacts: [...state.contacts, action.payload]
      }
    case ContactActions.UPDATE_CONTACT:
      const contact = state.contacts[action.payload.id];
      const updatedContact = {
        ...contact,
        ...action.payload.value
      };
      const contacts = [...state.contacts];
      contacts[action.payload.id] = updatedContact;
      return {
        ...state,
        contacts: contacts
      };
      case ContactActions.DELETE_CONTACT:
        const Oldcontacts = [...state.contacts];
        contacts.splice(action.payload, 1);
        return {
          ...state,
          contacts: Oldcontacts
        };
    default:
      return state;
  }
}