package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.urfu.idea.mapper.IUserMapper;
import ru.urfu.idea.model.User;
import ru.urfu.idea.repository.IUserRepository;
import ru.urfu.idea.request.UserRequest;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserService implements IUserService, UserDetailsService {
	
	private final IUserRepository userRepository;
	private final IUserMapper mapper;
	
	@Override
	public User register(UserRequest userRequest) {
		User user = mapper.requestToModel(userRequest);
		
		return userRepository.save(user);
	}
	
	@Override
	public User update(long id, User user) {
		User currentUser = findById(id);
		
		currentUser.setUsername(user.getUsername());
		currentUser.setPassword(user.getPassword());
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setPatronymic(user.getPatronymic());
		currentUser.setRoles(user.getRoles());
		
		return userRepository.saveAndFlush(currentUser);
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public User findById(long id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public void delete(long id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public User loadUserByUsername(String s) throws UsernameNotFoundException {
		return userRepository.findByUsername(s).orElseThrow();
	}
	
}
