package ru.urfu.idea.service;

import ru.urfu.idea.entity.Vote;

import java.util.Collection;

public interface IVoteService {
	
	Vote create(long votingId, Vote vote);
	
	Vote update(long id, Vote vote);
	
	Collection<Vote> findAll();
	
	Vote findById(long id);
	
	void delete(long id);
	
}
