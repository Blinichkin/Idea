package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.mapper.IRoleMapper;
import ru.urfu.idea.model.Role;
import ru.urfu.idea.repository.IRoleRepository;
import ru.urfu.idea.request.RoleRequest;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RoleService implements IRoleService {
	
	private final IRoleRepository repository;
	private final IRoleMapper mapper;
	
	@Override
	public Role create(RoleRequest roleRequest) {
		Role role = mapper.requestToModel(roleRequest);
		return repository.save(role);
	}
	
	@Override
	public Role update(long id, RoleRequest roleRequest) {
		Role currentRole = findById(id);
		
		currentRole.setName(roleRequest.getName());
		currentRole.setPermission(roleRequest.getPermission());
		
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
