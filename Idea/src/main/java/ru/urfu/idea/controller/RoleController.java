package ru.urfu.idea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.entity.Role;
import ru.urfu.idea.mapper.request.RoleRequest;
import ru.urfu.idea.service.IRoleService;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
	
	private final IRoleService service;
	
	@PostMapping
	public ResponseEntity<Role> create(@RequestBody @Validated final RoleRequest roleRequest)  {
		Role createdRole = service.create(roleRequest);
		return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Role> update(@PathVariable("id") final long id,
									   @RequestBody @Validated final RoleRequest roleRequest) {
		Role updatedRole = service.update(id, roleRequest);
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
