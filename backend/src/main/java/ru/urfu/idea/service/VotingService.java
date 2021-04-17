package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.*;
import ru.urfu.idea.exception.AppException;
import ru.urfu.idea.repository.*;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class VotingService implements IVotingService {
	
	private final IVotingRepository votingRepository;
	private final IVoteRepository voteRepository;
	private final IVotingStatusRepository votingStatusRepository;
	private final IVotingTypeRepository votingTypeRepository;
	private final IIdeaRepository ideaRepository;
	
	@Override
	public Voting create(final long ideaId, final Voting voting) {
		Idea idea = ideaRepository.findById(ideaId)
				.orElseThrow(() -> new AppException("Idea not found", HttpStatus.NOT_FOUND));
		
		VotingStatus status = votingStatusRepository.findByName(VotingStatusEnum.ACTIVE.getName())
				.orElseThrow(() -> new AppException("Voting status not found", HttpStatus.NOT_FOUND));
		
		VotingType type = votingTypeRepository.findByName(voting.getType().getName().getName())
				.orElseThrow(() -> new AppException("Voting type status not found", HttpStatus.NOT_FOUND));
		
		Voting newVoting = new Voting();
		newVoting.setIdea(idea);
		newVoting.setRequiredVotes(voting.getRequiredVotes());
		newVoting.setType(type);
		newVoting.setStatus(status);
		
		return votingRepository.save(newVoting);
	}
	
	@Override
	public Voting update(final long id, final Voting voting) {
		Voting currentVoting = votingRepository.findById(id)
				.orElseThrow(() -> new AppException("Voting not found", HttpStatus.NOT_FOUND));
		
		currentVoting.setRequiredVotes(voting.getRequiredVotes());
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
	
	@Override
	public Long votesCount(long id) {
		return voteRepository.getVotesCount(id);
	}
	
	@Override
	public Long votesFor(long id) {
		return voteRepository.getVotesFor(id);
	}
	
	@Override
	public Long votesAgainst(long id) {
		return voteRepository.getVotesAgainst(id);
	}
	
	@Override
	public Long votesResult(long id) {
		Long votesFor = voteRepository.getVotesFor(id);
		Long votesAgainst = voteRepository.getVotesAgainst(id);
		
		return votesFor - votesAgainst;
	}
	
}
