package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.mapper.IContactMapper;
import ru.urfu.idea.model.Contact;
import ru.urfu.idea.repository.IContactRepository;
import ru.urfu.idea.request.ContactRequest;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ContactService implements IContactService {
	
	private final IContactRepository repository;
	private final IContactMapper mapper;
	
	@Override
	public Contact create(ContactRequest contactRequest) {
		Contact contact = mapper.requestToModel(contactRequest);
		return repository.save(contact);
	}
	
	@Override
	public Contact update(long id, ContactRequest contactRequest) {
		Contact currentContact = findById(id);
		
		currentContact.setPhoneNumber(contactRequest.getPhoneNumber());
		currentContact.setEmail(contactRequest.getEmail());
		
		return repository.saveAndFlush(currentContact);
	}
	
	@Override
	public List<Contact> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Contact findById(long id) {
		return repository.findById(id).get();
	}
	
	@Override
	public void delete(long id) {
		repository.deleteById(id);
	}
	
}
