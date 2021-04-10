package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.entity.Role;
import ru.urfu.idea.mapper.request.RoleRequest;

@Mapper(componentModel = "spring")
public interface IRoleMapper {
	
	Role requestToModel(RoleRequest roleRequest);
	
}
