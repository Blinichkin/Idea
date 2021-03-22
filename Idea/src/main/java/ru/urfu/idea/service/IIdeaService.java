package ru.urfu.idea.service;

import ru.urfu.idea.model.Idea;

import java.util.List;

public interface IIdeaService {
	
	public Idea create(Idea idea);
	
	public Idea update(long id, Idea idea);
	
	public List<Idea> findAll();
	
	public Idea findById(long id);
	
	public void delete(long id);

}
