package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.Contact;
import ru.urfu.idea.repository.IContactRepository;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ContactService implements IContactService {
	
	private final IContactRepository contactRepository;
	
	@Override
	public Contact create(final Contact contact) {
		Contact newContact = new Contact();
		newContact.setEmail(contact.getEmail());
		newContact.setPhoneNumber(contact.getPhoneNumber());
		
		return contactRepository.save(newContact);
	}
	
	@Override
	public Contact update(final long id, final Contact contact) {
		Contact currentContact = contactRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Contact not found"));
		
		currentContact.setEmail(contact.getEmail());
		currentContact.setPhoneNumber(contact.getPhoneNumber());
		
		return contactRepository.saveAndFlush(currentContact);
	}
	
	@Override
	public Collection<Contact> findAll() {
		return contactRepository.findAll();
	}
	
	@Override
	public Contact findById(final long id) {
		return contactRepository.findById(id).orElse(null);
	}
	
	@Override
	public Contact delete(final long id) {
		Contact contact = contactRepository.findById(id).orElse(null);
		if (contact != null) {
			contactRepository.deleteById(id);
		}
		
		return contact;
	}
	
}
