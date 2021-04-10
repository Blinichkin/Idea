package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.entity.Idea;
import ru.urfu.idea.mapper.request.IdeaRequest;
import ru.urfu.idea.mapper.response.IdeaResponse;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IIdeaMapper {
	
	Idea requestToModel(IdeaRequest ideaRequest);
	
	IdeaResponse modelToResponse(Idea idea);
	
	Collection<IdeaResponse> modelToResponse(Collection<Idea> ideas);
	
}
