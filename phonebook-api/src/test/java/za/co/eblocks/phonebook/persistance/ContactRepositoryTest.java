package za.co.eblocks.phonebook.persistance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import za.co.eblocks.phonebook.persistance.Repository.ContactRepository;
import za.co.eblocks.phonebook.persistance.entity.Contact;

import java.util.Date;
import java.util.Optional;
@DataJpaTest
@ActiveProfiles({"test"})
class ContactRepositoryTest {
    private static final long id =3;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    @Sql({"/insertContacts.sql"})
    @Sql(scripts= "/deleteContacts.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testFindByName() {
        Optional<Contact> optionalContact = contactRepository.findByName("Troy");
        Assertions.assertTrue(optionalContact.isPresent());
        Contact foundContact = optionalContact.get();
        Assertions.assertEquals("Troy", foundContact.getName(),"[Checking if name is as expected]");
        Assertions.assertEquals("troy@dance.com", foundContact.getEmail(), "[Checking if email is as expected]");
        Assertions.assertEquals("0798521456", foundContact.getNumber(), "[Checking if number is as expected]");
        Assertions.assertEquals("Other", foundContact.getType(), "[Checking if type is as expected]");
    }

    @Test
    @Sql({"/insertContacts.sql"})
    @Sql(scripts= "/deleteContacts.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testFindById() {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        Assertions.assertTrue(optionalContact.isPresent());
        Contact foundContact = optionalContact.get();
        Assertions.assertEquals(id, foundContact.getId(),"[Checking if name is as expected]");
        Assertions.assertEquals("Zuma", foundContact.getName(),"[Checking if name is as expected]");
        Assertions.assertEquals("zuma@jacob.co.uk", foundContact.getEmail(), "[Checking if email is as expected]");
        Assertions.assertEquals("072678456", foundContact.getNumber(), "[Checking if number is as expected]");
        Assertions.assertEquals("Work", foundContact.getType(), "[Checking if type is as expected]");
    }

    @Test
    @Sql(scripts= "/deleteContacts.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCreateContact() {
        Contact contact = new Contact(5L,"James","james@jump.yes.com","0647583659","Work", new Date());
        contactRepository.save(contact);
        Optional<Contact> optionalContact = contactRepository.findByEmail("james@jump.yes.com");

        Assertions.assertTrue(optionalContact.isPresent());
        Contact savedContact = optionalContact.get();
        Assertions.assertNotNull(contact);
        Assertions.assertEquals(savedContact.getEmail(), contact.getEmail());
        Assertions.assertEquals(savedContact.getType(), contact.getType());
    }

    @Test
    @Sql({"/insertContacts.sql"})
    @Sql(scripts= "/deleteContacts.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testFindAllContacts() {
        Assertions.assertNotNull(contactRepository.findAll());
    }

    @Test
    void testDeleteContact() {
        Contact contact = new Contact(null,"James","james@jump.yes.com","0647583659","Work", new Date());
        contactRepository.save(contact);
        Assertions.assertNotNull(contact);
        contactRepository.delete(contact);
    }
    @Test
    void testDeleteContactById() {
        Contact contact = new Contact(null,"James.com","james@jump.yes.com","0647583659","Work", new Date());
        Contact cont = contactRepository.save(contact);
        contactRepository.deleteById(cont.getId());
    }

}
