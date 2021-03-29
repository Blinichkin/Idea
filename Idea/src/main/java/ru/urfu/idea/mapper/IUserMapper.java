package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.model.User;
import ru.urfu.idea.request.UserRequest;

@Mapper(componentModel = "spring")
public interface IUserMapper {
	
	User requestToModel(UserRequest userRequest);
	
}
