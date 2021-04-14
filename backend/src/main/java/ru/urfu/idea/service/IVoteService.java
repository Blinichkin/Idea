package ru.urfu.idea.service;

import ru.urfu.idea.entity.Vote;

import java.util.Collection;

public interface IVoteService {
	
	Vote create(long userId, long votingId, Vote vote);
	
	Vote update(long id, Vote vote);
	
	Collection<Vote> findAll();
	
	Vote findById(long id);
	
	Vote delete(long id);
	
}
