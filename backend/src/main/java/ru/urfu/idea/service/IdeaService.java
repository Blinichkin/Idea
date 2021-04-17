package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.*;
import ru.urfu.idea.exception.AppException;
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
				.orElseThrow(() -> new AppException("Status not found", HttpStatus.NOT_FOUND));
		
		Idea newIdea = new Idea();
		newIdea.setName(idea.getName());
		newIdea.setText(idea.getText());
		newIdea.setCost(idea.getCost());
		newIdea.setContact(idea.getContact());
		newIdea.setAddress(idea.getAddress());
		newIdea.setAttachments(idea.getAttachments());
		newIdea.setStatus(status);
		
		return ideaRepository.save(newIdea);
	}
	
	@Override
	public Idea update(final long id, final Idea idea) {
		Idea currentIdea = ideaRepository.findById(id)
				.orElseThrow(() -> new AppException("Idea not found", HttpStatus.NOT_FOUND));
		
		currentIdea.setName(idea.getName());
		currentIdea.setText(idea.getText());
		currentIdea.setCost(idea.getCost());
		currentIdea.setContact(idea.getContact());
		currentIdea.setAddress(idea.getAddress());
		currentIdea.setAttachments(idea.getAttachments());
		
		return ideaRepository.saveAndFlush(currentIdea);
	}
	
	@Override
	public Collection<Idea> findAll() {
		return ideaRepository.findAll();
	}
	
	@Override
	public Idea findById(final long id) {
		return ideaRepository.findById(id).orElse(null);
	}
	
	@Override
	public Idea delete(final long id) {
		Idea idea = ideaRepository.findById(id).orElse(null);
		if (idea != null) {
			ideaRepository.deleteById(idea.getId());
		}
		
		return idea;
	}
	
	@Override
	public Idea submit(final long id) {
		Idea currentIdea = ideaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Idea not found"));
		IdeaStatus status = statusRepository.findByName(IdeaStatusEnum.MODERATION.getName())
				.orElseThrow(() -> new RuntimeException("Status not found"));
		
		currentIdea.setStatus(status);
		
		return ideaRepository.saveAndFlush(currentIdea);
	}
	
	@Override
	public Collection<Idea> findAllByStatusName(String name) {
		IdeaStatus status = statusRepository.findByName(name)
				.orElseThrow(() -> new RuntimeException("Status not found"));
		
		return ideaRepository.findAllByStatus(status.getId());
	}
	
}
