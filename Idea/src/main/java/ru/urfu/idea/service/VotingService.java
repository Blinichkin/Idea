package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.*;
import ru.urfu.idea.repository.IIdeaRepository;
import ru.urfu.idea.repository.IVotingRepository;
import ru.urfu.idea.repository.IVotingStatusRepository;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class VotingService implements IVotingService {
	
	private final IVotingRepository votingRepository;
	private final IVotingStatusRepository votingStatusRepository;
	private final IIdeaRepository ideaRepository;
	
	@Override
	public Voting create(final long ideaId, final Voting voting) {
		Idea idea = ideaRepository.findById(ideaId)
				.orElseThrow(() -> new RuntimeException("Idea not found"));
		
		VotingStatus status = votingStatusRepository.findByName(VotingStatusEnum.ACTIVE.getName())
				.orElseThrow(() -> new RuntimeException("Voting status not found"));
		
		Voting newVoting = new Voting();
		newVoting.setIdea(idea);
		newVoting.setStatus(status);
		
		return votingRepository.save(newVoting);
	}
	
	@Override
	public Voting update(final long id, final Voting voting) {
		Voting currentVoting = votingRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Voting not found"));
		
		currentVoting.setStatus(voting.getStatus());
		
		return votingRepository.saveAndFlush(currentVoting);
	}
	
	@Override
	public Collection<Voting> findAll() {
		return votingRepository.findAll();
	}
	
	@Override
	public Voting findById(final long id) {
		return votingRepository.findById(id).orElse(null);
	}
	
	@Override
	public Voting delete(final long id) {
		Voting voting = votingRepository.findById(id).orElse(null);
		if (voting != null) {
			votingRepository.deleteById(voting.getId());
		}
		
		return voting;
	}
	
}
