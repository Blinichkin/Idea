package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.entity.Cost;
import ru.urfu.idea.mapper.request.CostRequest;

@Mapper(componentModel = "spring")
public interface ICostMapper {
	
	Cost requestToModel(CostRequest costRequest);
	
}
