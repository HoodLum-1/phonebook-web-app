package za.co.eblocks.phonebook.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.eblocks.phonebook.persistance.entity.Contact;
import za.co.eblocks.phonebook.persistance.Repository.ContactRepository;

import java.util.List;

@RestController
@RequestMapping("contacts")
@Slf4j
@CrossOrigin(origins = "*")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping(value = "v1")
    public List<Contact> getAllContacts() {
        log.info("Getting all contacts");
        return contactRepository.findAll();
    }

    @GetMapping(value = "v1/{id}")
    public Contact getContactById(@PathVariable("id") final Long id) {
        log.info("Getting contact by Id={{}]", id);
        return contactRepository.findById(id).get();
    }

    @PostMapping(value = "v1")
    public void createContact(@RequestBody Contact contact) {
        log.info("Creating contact Name=[{}]", contact.getName());
        contactRepository.save(contact);
    }

    @PutMapping(value = "v1/{id}")
    public void updateContact(@PathVariable("id") final Long id, @RequestBody final Contact contact) {
        log.info("Updating contact. Name=[{}]", contact.getName());
        contactRepository.findById(id).ifPresent(c -> contactRepository.save(contact));
    }

    @DeleteMapping(value = "v1/{id}")
    public void deleteContact(@PathVariable("id") final Long id) {
        log.info("Deleting contact by Id={{}]", id);

        contactRepository.deleteById(id);
    }
}
