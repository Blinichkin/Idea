package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.entity.IdeaStatus;
import ru.urfu.idea.mapper.request.IdeaStatusRequest;

@Mapper(componentModel = "spring")
public interface IIdeaStatusMapper {
	
	IdeaStatus requestToModel(IdeaStatusRequest statusRequest);
	
}
