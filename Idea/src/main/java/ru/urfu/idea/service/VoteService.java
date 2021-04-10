package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.*;
import ru.urfu.idea.repository.IVoteRepository;
import ru.urfu.idea.repository.IVotingRepository;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class VoteService implements IVoteService {
	
	private final IVoteRepository voteRepository;
	private final IVotingRepository votingRepository;
	
	@Override
	public Vote create(long votingId, Vote vote) {
		Voting voting = votingRepository.findById(votingId)
				.orElseThrow(() -> new RuntimeException("Voting not found"));
		
		Vote newVote = new Vote();
		newVote.setVoting(voting);
		newVote.setOptionAnswer(vote.getOptionAnswer());
		
		return voteRepository.save(newVote);
	}
	
	@Override
	public Vote update(long id, Vote vote) {
		Vote currentVote = voteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vote not found"));
		
		currentVote.setOptionAnswer(vote.getOptionAnswer());
		
		return voteRepository.saveAndFlush(currentVote);
	}
	
	@Override
	public Collection<Vote> findAll() {
		return voteRepository.findAll();
	}
	
	@Override
	public Vote findById(long id) {
		return voteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vote not found"));
	}
	
	@Override
	public void delete(long id) {
		Vote vote = voteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vote not found"));
		
		voteRepository.deleteById(vote.getId());
	}
	
}
