package ru.urfu.idea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.entity.Contact;
import ru.urfu.idea.mapper.IContactMapper;
import ru.urfu.idea.mapper.request.ContactRequest;
import ru.urfu.idea.security.UserPrincipal;
import ru.urfu.idea.service.IContactService;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {
	
	private final IContactService contactService;
	private final IContactMapper contactMapper;
	
	@PostMapping
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Contact> create(@RequestBody @Validated final ContactRequest contactRequest) {
		Contact contact = contactMapper.requestToModel(contactRequest);
		Contact createdContact = contactService.create(contact);
		
		return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN') || (hasAuthority('USER') && #id == #userPrincipal.id)")
	public ResponseEntity<Contact> update(@AuthenticationPrincipal UserPrincipal userPrincipal,
										  @PathVariable("id") final long id,
										  @RequestBody @Validated final ContactRequest contactRequest) {
		Contact contact = contactMapper.requestToModel(contactRequest);
		Contact updatedContact = contactService.update(id, contact);
		
		return new ResponseEntity<>(updatedContact, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<Contact>> getAll() {
		Collection<Contact> contacts = contactService.findAll();
		
		return new ResponseEntity<>(contacts, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Contact> getById(@PathVariable("id") final long id) {
		Contact contact = contactService.findById(id);
		if (contact == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(contact, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Contact> delete(@PathVariable("id") final long id) {
		Contact contact = contactService.delete(id);
		if (contact == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
