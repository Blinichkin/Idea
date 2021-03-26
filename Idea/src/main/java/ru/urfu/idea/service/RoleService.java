package ru.urfu.idea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.model.Role;
import ru.urfu.idea.repository.IRoleRepository;

import java.util.List;

@Service
public class RoleService implements IRoleService {
	
	private final IRoleRepository repository;
	
	@Autowired
	public RoleService(IRoleRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Role create(Role role) {
		return repository.save(role);
	}
	
	@Override
	public Role update(long id, Role role) {
		Role currentRole = findById(id);
		
		currentRole.setName(role.getName());
		currentRole.setPermission(role.getPermission());
		
		return repository.saveAndFlush(currentRole);
	}
	
	@Override
	public List<Role> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Role findById(long id) {
		return repository.findById(id).get();
	}
	
	@Override
	public void delete(long id) {
		repository.deleteById(id);
	}
	
}
