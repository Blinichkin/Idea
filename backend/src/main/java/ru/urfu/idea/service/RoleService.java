package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.Role;
import ru.urfu.idea.repository.IRoleRepository;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RoleService implements IRoleService {
	
	private final IRoleRepository roleRepository;
	
	@Override
	public Role create(final Role role) {
		Role newRole = new Role();
		newRole.setName(role.getName());
		
		return roleRepository.save(role);
	}
	
	@Override
	public Role update(final long id, final Role role) {
		Role currentRole = roleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Role not found"));
		
		currentRole.setName(role.getName());
		
		return roleRepository.saveAndFlush(currentRole);
	}
	
	@Override
	public Collection<Role> findAll() {
		return roleRepository.findAll();
	}
	
	@Override
	public Role findById(final long id) {
		return roleRepository.findById(id).orElse(null);
	}
	
	@Override
	public Role delete(final long id) {
		Role role = roleRepository.findById(id).orElse(null);
		if (role != null) {
			roleRepository.deleteById(role.getId());
		}
		
		return role;
	}
	
}
