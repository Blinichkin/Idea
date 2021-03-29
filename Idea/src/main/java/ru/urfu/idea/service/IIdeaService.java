package ru.urfu.idea.service;

import ru.urfu.idea.model.Idea;
import ru.urfu.idea.request.IdeaRequest;

import java.util.List;

public interface IIdeaService {
	
	public Idea create(long userId, IdeaRequest ideaRequest);
	
	public Idea update(long id, Idea idea);
	
	public List<Idea> findAll();
	
	public Idea findById(long id);
	
	public void delete(long id);

}
