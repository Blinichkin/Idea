package ru.urfu.idea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.model.Idea;
import ru.urfu.idea.repository.IIdeaRepository;

import java.util.List;

@Service
public class IdeaService implements IIdeaService {

	private final IIdeaRepository repository;
	
	@Autowired
	public IdeaService(IIdeaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Idea create(Idea idea) {
		return repository.save(idea);
	}
	
	@Override
	public Idea update(long id, Idea idea) {
		Idea currentIdea = findById(id);
		
		currentIdea.setName(idea.getName());
		currentIdea.setText(idea.getText());
		
		return repository.saveAndFlush(currentIdea);
	}
	
	@Override
	public List<Idea> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Idea findById(long id) {
		return repository.findById(id).get();
	}
	
	@Override
	public void delete(long id) {
		repository.deleteById(id);
	}
	
}
