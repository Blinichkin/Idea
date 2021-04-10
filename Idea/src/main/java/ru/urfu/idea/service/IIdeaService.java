package ru.urfu.idea.service;

import ru.urfu.idea.entity.Idea;

import java.util.Collection;

public interface IIdeaService {
	
	public Idea create(Idea idea);
	
	public Idea update(long id, Idea idea);
	
	public Collection<Idea> findAll();
	
	public Idea findById(long id);
	
	public void delete(long id);

}
