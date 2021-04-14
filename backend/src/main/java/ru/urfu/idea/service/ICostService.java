package ru.urfu.idea.service;

import ru.urfu.idea.entity.Cost;

import java.util.Collection;

public interface ICostService {
	
	Cost create(Cost cost);
	
	Cost update(long id, Cost cost);
	
	Collection<Cost> findAll();
	
	Cost findById(long id);
	
	Cost delete(long id);
	
}
