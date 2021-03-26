package ru.urfu.idea.service;

import ru.urfu.idea.model.Role;

import java.util.List;

public interface IRoleService {
	
	public Role create(Role role);
	
	public Role update(long id, Role role);
	
	public List<Role> findAll();
	
	public Role findById(long id);
	
	public void delete(long id);
	
}
