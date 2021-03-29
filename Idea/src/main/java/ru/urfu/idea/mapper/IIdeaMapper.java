package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.model.Idea;
import ru.urfu.idea.request.IdeaRequest;

@Mapper(componentModel = "spring")
public interface IIdeaMapper {
	
	Idea requestToModel(IdeaRequest ideaRequest);
	
}
