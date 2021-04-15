package ru.urfu.idea.service;

import ru.urfu.idea.entity.VotingType;

import java.util.Collection;

public interface IVotingTypeService {
	
	VotingType create(VotingType votingType);
	
	VotingType update(long id, VotingType votingType);
	
	Collection<VotingType> findAll();
	
	VotingType findById(long id);
	
	VotingType delete(long id);
	
}
