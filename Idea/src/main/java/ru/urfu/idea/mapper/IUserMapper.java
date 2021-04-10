package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.entity.User;
import ru.urfu.idea.mapper.request.UserRegister;
import ru.urfu.idea.mapper.response.UserResponse;
import ru.urfu.idea.security.UserPrincipal;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IUserMapper {
	
	User requestToModel(UserRegister userRegister);
	
	UserResponse modelToResponse(User user);
	
	Collection<UserResponse> modelToResponse(Collection<User> users);
	
	UserPrincipal modelToPrincipal(User user);
	
}
