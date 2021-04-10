package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.Idea;
import ru.urfu.idea.entity.IdeaStatus;
import ru.urfu.idea.entity.IdeaStatusEnum;
import ru.urfu.idea.repository.IIdeaRepository;
import ru.urfu.idea.repository.IIdeaStatusRepository;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class IdeaService implements IIdeaService {

	private final IIdeaRepository ideaRepository;
	private final IIdeaStatusRepository statusRepository;
	
	@Override
	public Idea create(final Idea idea) {
		IdeaStatus status = statusRepository.findByName(IdeaStatusEnum.NEW.getName())
				.orElseThrow(() -> new RuntimeException("Status not found"));
		
		Idea newIdea = new Idea();
		newIdea.setName(idea.getName());
		newIdea.setText(idea.getText());
		newIdea.setContact(idea.getContact());
		newIdea.setStatus(status);
		
		return ideaRepository.save(newIdea);
	}
	
	@Override
	public Idea update(final long id, final Idea idea) {
		Idea currentIdea = ideaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Idea not found"));
		
		currentIdea.setName(idea.getName());
		currentIdea.setText(idea.getText());
		currentIdea.setContact(idea.getContact());
		
		return ideaRepository.saveAndFlush(currentIdea);
	}
	
	@Override
	public Collection<Idea> findAll() {
		return ideaRepository.findAll();
	}
	
	@Override
	public Idea findById(long id) {
		return ideaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Idea not found"));
	}
	
	@Override
	public void delete(long id) {
		Idea idea = ideaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Idea not found"));
		
		ideaRepository.deleteById(idea.getId());
	}
	
}
