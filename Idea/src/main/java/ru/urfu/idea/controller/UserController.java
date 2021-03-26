package ru.urfu.idea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.model.User;
import ru.urfu.idea.service.IUserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private final IUserService service;
	
	public UserController(IUserService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody @Valid final User user)  {
		service.create(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable("id") final long id,
									   @RequestBody @Valid final User user) {
		User updatedUser = service.update(id, user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		List<User> users = service.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") final long id) {
		User user = service.findById(id);
		
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> delete(@PathVariable("id") final long id) {
		User user = service.findById(id);
		
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
