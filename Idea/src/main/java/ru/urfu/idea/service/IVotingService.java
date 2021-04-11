package ru.urfu.idea.service;

import ru.urfu.idea.entity.Voting;

import java.util.Collection;

public interface IVotingService {
	
	Voting create(long ideaId, Voting voting);
	
	Voting update(long id, Voting voting);
	
	Collection<Voting> findAll();
	
	Voting findById(long id);
	
	Voting delete(long id);
	
	Long votesCount(long id);
	
	Long votesFor(long id);
	
	Long votesAgainst(long id);
	
	Long votesResult(long id);
	
}
