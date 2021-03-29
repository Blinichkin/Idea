package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.mapper.IIdeaMapper;
import ru.urfu.idea.model.*;
import ru.urfu.idea.repository.IIdeaRepository;
import ru.urfu.idea.repository.IIdeaStatusRepository;
import ru.urfu.idea.repository.IUserRepository;
import ru.urfu.idea.request.IdeaRequest;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class IdeaService implements IIdeaService {

	private final IIdeaRepository ideaRepository;
	private final IIdeaStatusRepository statusRepository;
	private final IUserRepository userRepository;
	
	private final IIdeaMapper mapper;
	
	@Override
	public Idea create(long userId, IdeaRequest ideaRequest) {
		Idea idea = mapper.requestToModel(ideaRequest);
		
		IdeaStatus status = statusRepository.findByName(IdeaStatusEnum.NEW.getName()).get(0);
		User author = userRepository.findById(userId).get();
		
		idea.setStatus(status);
		idea.setAuthor(author);
		
		return ideaRepository.save(idea);
	}
	
	@Override
	public Idea update(long id, Idea idea) {
		Idea currentIdea = findById(id);
		
		currentIdea.setName(idea.getName());
		currentIdea.setText(idea.getText());
		
		return ideaRepository.saveAndFlush(currentIdea);
	}
	
	@Override
	public List<Idea> findAll() {
		return ideaRepository.findAll();
	}
	
	@Override
	public Idea findById(long id) {
		return ideaRepository.findById(id).get();
	}
	
	@Override
	public void delete(long id) {
		ideaRepository.deleteById(id);
	}
	
}
