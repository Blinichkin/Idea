package ru.urfu.idea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.entity.Role;
import ru.urfu.idea.mapper.IRoleMapper;
import ru.urfu.idea.mapper.request.RoleRequest;
import ru.urfu.idea.service.IRoleService;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
	
	private final IRoleService roleService;
	private final IRoleMapper roleMapper;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Role> create(@RequestBody @Validated final RoleRequest roleRequest) {
		Role role = roleMapper.requestToModel(roleRequest);
		Role createdRole = roleService.create(role);
		
		return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Role> update(@PathVariable("id") final long id,
									   @RequestBody @Validated final RoleRequest roleRequest) {
		Role role = roleMapper.requestToModel(roleRequest);
		Role updatedRole = roleService.update(id, role);
		
		return new ResponseEntity<>(updatedRole, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<Role>> getAll() {
		Collection<Role> roles = roleService.findAll();
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Role> getById(@PathVariable("id") final long id) {
		Role role = roleService.findById(id);
		if (role == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(role, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Role> delete(@PathVariable("id") final long id) {
		Role role = roleService.delete(id);
		if (role == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(role, HttpStatus.OK);
	}
	
}
