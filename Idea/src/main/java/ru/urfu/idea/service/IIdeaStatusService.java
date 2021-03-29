package ru.urfu.idea.service;

import ru.urfu.idea.model.IdeaStatus;
import ru.urfu.idea.request.IdeaStatusRequest;

import java.util.List;

public interface IIdeaStatusService {
	
	public IdeaStatus create(IdeaStatusRequest statusRequest);
	
	public IdeaStatus update(long id, IdeaStatusRequest statusRequest);
	
	public List<IdeaStatus> findAll();
	
	public IdeaStatus findById(long id);
	
	public void delete(long id);
	
}
