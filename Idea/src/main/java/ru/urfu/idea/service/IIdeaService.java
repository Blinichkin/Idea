package ru.urfu.idea.service;

import ru.urfu.idea.entity.Idea;

import java.util.Collection;

public interface IIdeaService {
	
	Idea create(Idea idea);
	
	Idea update(long id, Idea idea);
	
	Collection<Idea> findAll();
	
	Idea findById(long id);
	
	Idea delete(long id);
	
	Idea submit(long id);
	
	Collection<Idea> findAllByStatusName(String name);

}
