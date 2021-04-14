package ru.urfu.idea.service;

import ru.urfu.idea.entity.CostType;

import java.util.Collection;

public interface ICostTypeService {
	
	CostType create(CostType type);
	
	CostType update(long id, CostType type);
	
	Collection<CostType> findAll();
	
	CostType findById(long id);
	
	CostType delete(long id);
	
}
