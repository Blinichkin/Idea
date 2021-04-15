package ru.urfu.idea.service;

import ru.urfu.idea.entity.VotingStatus;

import java.util.Collection;

public interface IVotingStatusService {
	
	VotingStatus create(VotingStatus votingStatus);
	
	VotingStatus update(long id, VotingStatus votingStatus);
	
	Collection<VotingStatus> findAll();
	
	VotingStatus findById(long id);
	
	VotingStatus delete(long id);
	
}
