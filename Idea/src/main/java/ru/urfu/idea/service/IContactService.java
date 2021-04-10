package ru.urfu.idea.service;

import ru.urfu.idea.entity.Contact;

import java.util.Collection;

public interface IContactService {
	
	Contact create(Contact contact);
	
	Contact update(long id, Contact contact);
	
	Collection<Contact> findAll();
	
	Contact findById(long id);
	
	Contact delete(long id);
	
}
