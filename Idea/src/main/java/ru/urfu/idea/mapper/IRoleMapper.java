package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.model.Role;
import ru.urfu.idea.request.RoleRequest;

@Mapper(componentModel = "spring")
public interface IRoleMapper {
	
	Role requestToModel(RoleRequest roleRequest);
	
}
