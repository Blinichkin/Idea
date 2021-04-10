package ru.urfu.idea.service;

import ru.urfu.idea.entity.Role;
import ru.urfu.idea.mapper.request.RoleRequest;

import java.util.List;

public interface IRoleService {
	
	public Role create(RoleRequest roleRequest);
	
	public Role update(long id, RoleRequest roleRequest);
	
	public List<Role> findAll();
	
	public Role findById(long id);
	
	public void delete(long id);
	
}
