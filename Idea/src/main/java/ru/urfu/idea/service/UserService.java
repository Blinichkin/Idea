package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.Role;
import ru.urfu.idea.entity.RoleEnum;
import ru.urfu.idea.entity.User;
import ru.urfu.idea.mapper.request.UserProfileRequest;
import ru.urfu.idea.repository.IRoleRepository;
import ru.urfu.idea.repository.IUserRepository;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserService implements IUserService {
	
	private final PasswordEncoder passwordEncoder;
	private final IUserRepository userRepository;
	private final IRoleRepository roleRepository;
	
	@Override
	public User create(final User user) {
		if (findByLogin(user.getLogin()) != null) {
			throw new RuntimeException("Login already exists");
		}
		
		User newUser = new User();
		newUser.setLogin(user.getLogin());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setLastName(user.getLastName());
		newUser.setFirstName(user.getFirstName());
		newUser.setPatronymic(user.getPatronymic());
		newUser.setRoles(createRoles());
		
		return userRepository.save(newUser);
	}
	
	private Collection<Role> createRoles() {
		Collection<Role> roles = new ArrayList<>();
		Role role = roleRepository.findByName(RoleEnum.USER.getName())
				.orElseThrow(() -> new RuntimeException("Role not found"));
		roles.add(role);
		
		return roles;
	}
	
	@Override
	public User update(final long id, final UserProfileRequest user) {
		User currentUser = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setPatronymic(user.getPatronymic());
		
		return userRepository.saveAndFlush(currentUser);
	}
	
	@Override
	public Collection<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public User findById(final long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	@Override
	public User findByLogin(final String login) {
		return userRepository.findByLogin(login).orElse(null);
	}
	
	@Override
	public User delete(final long id) {
		User user = userRepository.findById(id).orElse(null);
		if (user != null) {
			userRepository.deleteById(user.getId());
		}
		
		return user;
	}
	
}
