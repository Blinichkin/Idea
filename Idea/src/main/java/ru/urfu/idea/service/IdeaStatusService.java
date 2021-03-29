package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.mapper.IIdeaStatusMapper;
import ru.urfu.idea.model.IdeaStatus;
import ru.urfu.idea.repository.IIdeaStatusRepository;
import ru.urfu.idea.request.IdeaStatusRequest;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class IdeaStatusService implements IIdeaStatusService {
	
	private final IIdeaStatusRepository repository;
	private final IIdeaStatusMapper mapper;
	
	@Override
	public IdeaStatus create(IdeaStatusRequest statusRequest) {
		IdeaStatus status = mapper.requestToModel(statusRequest);
		return repository.save(status);
	}
	
	@Override
	public IdeaStatus update(long id, IdeaStatusRequest statusRequest) {
		IdeaStatus currentStatus = findById(id);
		
		currentStatus.setName(statusRequest.getName());
		
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
