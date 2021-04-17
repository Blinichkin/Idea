package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.*;
import ru.urfu.idea.exception.AppException;
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
				.orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
		Voting voting = votingRepository.findById(votingId)
				.orElseThrow(() -> new AppException("Voting not found", HttpStatus.NOT_FOUND));
		
		if (!checkAccess(user, voting.getType())) {
			throw new AppException("Inappropriate role", HttpStatus.BAD_REQUEST);
		}
		
		if (voting.getStatus().getName() == VotingStatusEnum.COMPLETED) {
			throw new AppException("Voting completed", HttpStatus.BAD_REQUEST);
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
				throw new AppException("Voting type not found", HttpStatus.NOT_FOUND);
		}
		
		return false;
	}
	
	@Override
	public Vote update(final long id, final Vote vote) {
		Vote currentVote = voteRepository.findById(id)
				.orElseThrow(() -> new AppException("Vote not found", HttpStatus.NOT_FOUND));
		
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
