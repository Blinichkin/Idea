package ru.urfu.idea.service;

import ru.urfu.idea.model.User;

import java.util.List;

public interface IUserService {
	
	public User create(User user);
	
	public User update(long id, User user);
	
	public List<User> findAll();
	
	public User findById(long id);
	
	public void delete(long id);
	
}
