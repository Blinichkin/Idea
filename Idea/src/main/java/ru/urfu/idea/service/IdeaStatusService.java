package ru.urfu.idea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.model.IdeaStatus;
import ru.urfu.idea.repository.IIdeaStatusRepository;

import java.util.List;

@Service
public class IdeaStatusService implements IIdeaStatusService {
	
	private final IIdeaStatusRepository repository;
	
	@Autowired
	public IdeaStatusService(IIdeaStatusRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public IdeaStatus create(IdeaStatus status) {
		return repository.save(status);
	}
	
	@Override
	public IdeaStatus update(long id, IdeaStatus status) {
		IdeaStatus currentStatus = findById(id);
		
		currentStatus.setName(status.getName());
		
		return repository.saveAndFlush(currentStatus);
	}
	
	@Override
	public List<IdeaStatus> findAll() {
		return repository.findAll();
	}
	
	@Override
	public IdeaStatus findById(long id) {
		return repository.findById(id).get();
	}
	
	@Override
	public void delete(long id) {
		repository.deleteById(id);
	}
	
}
