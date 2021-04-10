package ru.urfu.idea.service;

import ru.urfu.idea.entity.User;
import ru.urfu.idea.mapper.request.UserProfileRequest;

import java.util.Collection;

public interface IUserService {
	
	User create(User user);
	
	User update(long id, UserProfileRequest userProfileRequest);
	
	Collection<User> findAll();
	
	User findById(long id);
	
	User findByLogin(String login);
	
	User delete(long id);
	
}
