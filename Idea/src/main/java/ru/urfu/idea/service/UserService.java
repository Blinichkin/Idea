package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.mapper.IUserMapper;
import ru.urfu.idea.model.Role;
import ru.urfu.idea.model.RoleEnum;
import ru.urfu.idea.model.User;
import ru.urfu.idea.repository.IRoleRepository;
import ru.urfu.idea.repository.IUserRepository;
import ru.urfu.idea.request.UserRequest;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserService implements IUserService {
	
	private final IUserRepository userRepository;
	private final IRoleRepository roleRepository;
	private final IUserMapper mapper;
	
	@Override
	public User register(UserRequest userRequest) {
		User user = mapper.requestToModel(userRequest);
		
		Role role = roleRepository.findByName(RoleEnum.USER.getName()).get(0);
		user.setRole(role);
		
		return userRepository.save(user);
	}
	
	@Override
	public User update(long id, User user) {
		User currentUser = findById(id);
		
		currentUser.setLogin(user.getLogin());
		currentUser.setPassword(user.getPassword());
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setPatronymic(user.getPatronymic());
		currentUser.setRole(user.getRole());
		
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
	
}
