package ru.urfu.idea.service;

import ru.urfu.idea.entity.Role;

import java.util.Collection;

public interface IRoleService {
	
	Role create(Role role);
	
	Role update(long id, Role role);
	
	Collection<Role> findAll();
	
	Role findById(long id);
	
	Role delete(long id);
	
}
