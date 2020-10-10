package za.co.eblocks.phonebook.persistance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.eblocks.phonebook.persistance.entity.Contact;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByName(String name);
    Optional<Contact> findByEmail(String email);
}
