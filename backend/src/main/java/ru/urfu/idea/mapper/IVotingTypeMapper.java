package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.entity.VotingType;
import ru.urfu.idea.mapper.request.VotingTypeRequest;

@Mapper(componentModel = "spring")
public interface IVotingTypeMapper {
	
	VotingType requestToModel(VotingTypeRequest typeRequest);
	
}
