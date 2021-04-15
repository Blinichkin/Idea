package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.*;
import ru.urfu.idea.repository.IUserRepository;
import ru.urfu.idea.repository.IVoteRepository;
import ru.urfu.idea.repository.IVotingRepository;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class VoteService implements IVoteService {
	
	private final IVoteRepository voteRepository;
	private final IVotingRepository votingRepository;
	private final IUserRepository userRepository;
	
	@Override
	public Vote create(final long userId, final long votingId, final Vote vote) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		Voting voting = votingRepository.findById(votingId)
				.orElseThrow(() -> new RuntimeException("Voting not found"));
		
		if (!checkAccess(user, voting.getType())) {
			throw new RuntimeException("Inappropriate role");
		}
		
		if (voting.getStatus().getName() == VotingStatusEnum.COMPLETED) {
			throw new RuntimeException("Voting completed");
		}
		
		Vote newVote = new Vote();
		newVote.setVoting(voting);
		newVote.setOptionAnswer(vote.isOptionAnswer());
		
		return voteRepository.save(newVote);
	}
	
	private boolean checkAccess(User user, VotingType votingType) {
		switch (votingType.getName()) {
			case STUDENT:
				boolean student = false;
				
				for (Role role: user.getRoles()) {
					if (role.getName() == RoleEnum.EXPERT) {
						return false;
					} else if (role.getName() == RoleEnum.USER) {
						student = true;
					}
				}
				
				return student;
			case EXPERT:
				for (Role role: user.getRoles()) {
					if (role.getName() == RoleEnum.EXPERT) {
						return true;
					}
				}
				break;
			default:
				throw new RuntimeException("Voting type not found");
		}
		
		return false;
	}
	
	@Override
	public Vote update(final long id, final Vote vote) {
		Vote currentVote = voteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Vote not found"));
		
		currentVote.setOptionAnswer(vote.isOptionAnswer());
		
		return voteRepository.saveAndFlush(currentVote);
	}
	
	@Override
	public Collection<Vote> findAll() {
		return voteRepository.findAll();
	}
	
	@Override
	public Vote findById(final long id) {
		return voteRepository.findById(id).orElse(null);
	}
	
	@Override
	public Vote delete(final long id) {
		Vote vote = voteRepository.findById(id).orElse(null);
		if (vote != null) {
			voteRepository.deleteById(vote.getId());
		}
		
		return vote;
	}
	
}
