package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.model.IdeaStatus;
import ru.urfu.idea.request.IdeaStatusRequest;

@Mapper(componentModel = "spring")
public interface IIdeaStatusMapper {
	
	IdeaStatus requestToModel(IdeaStatusRequest statusRequest);
	
}
