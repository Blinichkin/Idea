package ru.urfu.idea.service;

import ru.urfu.idea.model.Contact;
import ru.urfu.idea.request.ContactRequest;

import java.util.List;

public interface IContactService {
	
	public Contact create(ContactRequest contactRequest);
	
	public Contact update(long id, ContactRequest contactRequest);
	
	public List<Contact> findAll();
	
	public Contact findById(long id);
	
	public void delete(long id);
	
}
