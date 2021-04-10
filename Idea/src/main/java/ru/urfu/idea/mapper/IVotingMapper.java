package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.entity.Voting;
import ru.urfu.idea.mapper.request.VotingRequest;
import ru.urfu.idea.mapper.response.VotingResponse;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IVotingMapper {
	
	Voting requestToModel(VotingRequest votingRequest);
	
	VotingResponse modelToResponse(Voting voting);
	
	Collection<VotingResponse> modelToResponse(Collection<Voting> voting);
	
}
