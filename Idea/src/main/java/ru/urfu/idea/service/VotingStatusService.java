package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.VotingStatus;
import ru.urfu.idea.repository.IVotingStatusRepository;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class VotingStatusService implements IVotingStatusService {
	
	private final IVotingStatusRepository statusRepository;
	
	@Override
	public VotingStatus create(VotingStatus votingStatus) {
		return statusRepository.save(votingStatus);
	}
	
	@Override
	public VotingStatus update(long id, VotingStatus votingStatus) {
		VotingStatus currentStatus = statusRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Voting status not found"));
		
		currentStatus.setName(votingStatus.getName());
		
		return statusRepository.saveAndFlush(currentStatus);
	}
	
	@Override
	public Collection<VotingStatus> findAll() {
		return statusRepository.findAll();
	}
	
	@Override
	public VotingStatus findById(long id) {
		return statusRepository.findById(id).orElse(null);
	}
	
	@Override
	public void delete(long id) {
		VotingStatus status = statusRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Voting status not found"));
		
		statusRepository.deleteById(status.getId());
	}
	
}
