package ru.urfu.idea.service;

import ru.urfu.idea.entity.User;

import java.util.Collection;

public interface IUserService {
	
	public User create(User user);
	
	public User update(long id, User user);
	
	public Collection<User> findAll();
	
	public User findById(long id);
	
	public User findByLogin(String login);
	
	public void delete(long id);
	
}
