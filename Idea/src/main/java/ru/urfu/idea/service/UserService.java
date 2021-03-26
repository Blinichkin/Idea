package ru.urfu.idea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.model.User;
import ru.urfu.idea.repository.IUserRepository;

import java.util.List;

@Service
public class UserService implements IUserService {
	
	private final IUserRepository repository;
	
	@Autowired
	public UserService(IUserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public User create(User user) {
		return repository.save(user);
	}
	
	@Override
	public User update(long id,User user) {
		User currentUser = findById(id);
		
		currentUser.setLogin(user.getLogin());
		currentUser.setPassword(user.getPassword());
		currentUser.setRole(user.getRole());
		
		return repository.saveAndFlush(currentUser);
	}
	
	@Override
	public List<User> findAll() {
		return repository.findAll();
	}
	
	@Override
	public User findById(long id) {
		return repository.findById(id).get();
	}
	
	@Override
	public void delete(long id) {
		repository.deleteById(id);
	}
	
}
