package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.entity.VotingStatus;
import ru.urfu.idea.mapper.request.VotingStatusRequest;

@Mapper(componentModel = "spring")
public interface IVotingStatusMapper {
	
	VotingStatus requestToModel(VotingStatusRequest statusRequest);
	
}
