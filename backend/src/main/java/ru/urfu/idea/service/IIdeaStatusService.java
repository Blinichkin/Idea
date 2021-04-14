package ru.urfu.idea.service;

import ru.urfu.idea.entity.IdeaStatus;

import java.util.Collection;

public interface IIdeaStatusService {
	
	IdeaStatus create(IdeaStatus status);
	
	IdeaStatus update(long id, IdeaStatus status);
	
	Collection<IdeaStatus> findAll();
	
	IdeaStatus findById(long id);
	
	IdeaStatus delete(long id);
	
}
