package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.IdeaStatus;
import ru.urfu.idea.exception.AppException;
import ru.urfu.idea.repository.IIdeaStatusRepository;
import ru.urfu.idea.mapper.request.IdeaStatusRequest;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class IdeaStatusService implements IIdeaStatusService {
	
	private final IIdeaStatusRepository statusRepository;
	
	@Override
	public IdeaStatus create(final IdeaStatus status) {
		IdeaStatus newStatus = new IdeaStatus();
		newStatus.setName(status.getName());
		
		return statusRepository.save(newStatus);
	}
	
	@Override
	public IdeaStatus update(final long id, final IdeaStatus status) {
		IdeaStatus currentStatus = statusRepository.findById(id)
				.orElseThrow(() -> new AppException("Idea status not found", HttpStatus.NOT_FOUND));
		currentStatus.setName(status.getName());
		
		return statusRepository.saveAndFlush(currentStatus);
	}
	
	@Override
	public Collection<IdeaStatus> findAll() {
		return statusRepository.findAll();
	}
	
	@Override
	public IdeaStatus findById(final long id) {
		return statusRepository.findById(id).orElse(null);
	}
	
	@Override
	public IdeaStatus delete(long id) {
		IdeaStatus status = statusRepository.findById(id).orElse(null);
		if (status != null) {
			statusRepository.deleteById(id);
		}
		
		return status;
	}
	
}
