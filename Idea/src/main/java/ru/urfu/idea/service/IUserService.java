package ru.urfu.idea.service;

import ru.urfu.idea.entity.User;

import java.util.Collection;

public interface IUserService {
	
	User create(User user);
	
	User update(long id, User user);
	
	Collection<User> findAll();
	
	User findById(long id);
	
	User findByLogin(String login);
	
	void delete(long id);
	
}
