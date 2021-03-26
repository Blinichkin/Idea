package ru.urfu.idea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.model.Role;
import ru.urfu.idea.service.IRoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
	
	@Autowired
	private final IRoleService service;
	
	public RoleController(IRoleService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Role> create(@RequestBody @Valid final Role role)  {
		service.create(role);
		return new ResponseEntity<>(role, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Role> update(@PathVariable("id") final long id,
									   @RequestBody @Valid final Role role) {
		Role updatedRole = service.update(id, role);
		return new ResponseEntity<>(updatedRole, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Role>> getAll() {
		List<Role> roles = service.findAll();
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Role> getById(@PathVariable("id") final long id) {
		Role role = service.findById(id);
		
		if (role == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(role, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Role> delete(@PathVariable("id") final long id) {
		Role role = service.findById(id);
		
		if (role == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
