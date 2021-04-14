package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.entity.CostType;
import ru.urfu.idea.mapper.request.CostTypeRequest;

@Mapper(componentModel = "spring")
public interface ICostTypeMapper {
	
	CostType requestToModel(CostTypeRequest typeRequest);
	
}
