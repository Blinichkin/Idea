package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.VotingStatus;
import ru.urfu.idea.exception.AppException;
import ru.urfu.idea.repository.IVotingStatusRepository;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class VotingStatusService implements IVotingStatusService {
	
	private final IVotingStatusRepository statusRepository;
	
	@Override
	public VotingStatus create(final VotingStatus votingStatus) {
		return statusRepository.save(votingStatus);
	}
	
	@Override
	public VotingStatus update(final long id, final VotingStatus votingStatus) {
		VotingStatus currentStatus = statusRepository.findById(id)
				.orElseThrow(() -> new AppException("Voting status not found", HttpStatus.NOT_FOUND));
		
		currentStatus.setName(votingStatus.getName());
		
		return statusRepository.saveAndFlush(currentStatus);
	}
	
	@Override
	public Collection<VotingStatus> findAll() {
		return statusRepository.findAll();
	}
	
	@Override
	public VotingStatus findById(final long id) {
		return statusRepository.findById(id).orElse(null);
	}
	
	@Override
	public VotingStatus delete(final long id) {
		VotingStatus status = statusRepository.findById(id).orElseThrow(null);
		if (status != null) {
			statusRepository.deleteById(status.getId());
		}
		
		return status;
	}
	
}
