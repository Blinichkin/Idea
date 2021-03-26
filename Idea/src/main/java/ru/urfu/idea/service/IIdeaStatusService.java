package ru.urfu.idea.service;

import ru.urfu.idea.model.IdeaStatus;

import java.util.List;

public interface IIdeaStatusService {
	
	public IdeaStatus create(IdeaStatus status);
	
	public IdeaStatus update(long id, IdeaStatus status);
	
	public List<IdeaStatus> findAll();
	
	public IdeaStatus findById(long id);
	
	public void delete(long id);
	
}
