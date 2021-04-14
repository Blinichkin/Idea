package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.entity.Vote;
import ru.urfu.idea.mapper.request.VoteRequest;
import ru.urfu.idea.mapper.response.VoteResponse;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IVoteMapper {
	
	Vote requestToModel(VoteRequest voteRequest);
	
	VoteResponse modelToResponse(Vote vote);
	
	Collection<VoteResponse> modelToResponse(Collection<Vote> votes);
	
}
