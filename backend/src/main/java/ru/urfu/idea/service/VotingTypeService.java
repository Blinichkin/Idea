package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.VotingType;
import ru.urfu.idea.exception.AppException;
import ru.urfu.idea.repository.IVotingTypeRepository;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class VotingTypeService implements IVotingTypeService {
	
	private final IVotingTypeRepository votingTypeRepository;
	
	@Override
	public VotingType create(final VotingType type) {
		VotingType newType = new VotingType();
		newType.setName(type.getName());
		
		return votingTypeRepository.save(newType);
	}
	
	@Override
	public VotingType update(long id, VotingType type) {
		VotingType currentType = votingTypeRepository.findById(id)
				.orElseThrow(() -> new AppException("Voting type not found", HttpStatus.NOT_FOUND));
		currentType.setName(type.getName());
		
		return votingTypeRepository.saveAndFlush(currentType);
	}
	
	@Override
	public Collection<VotingType> findAll() {
		return votingTypeRepository.findAll();
	}
	
	@Override
	public VotingType findById(long id) {
		return votingTypeRepository.findById(id).orElse(null);
	}
	
	@Override
	public VotingType delete(long id) {
		VotingType type = votingTypeRepository.findById(id).orElse(null);
		if (type != null) {
			votingTypeRepository.deleteById(type.getId());
		}
		
		return type;
	}
	
}
