package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.CostType;
import ru.urfu.idea.exception.AppException;
import ru.urfu.idea.repository.ICostTypeRepository;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CostTypeService implements ICostTypeService {
	
	private final ICostTypeRepository typeRepository;
	
	@Override
	public CostType create(final CostType type) {
		CostType newType = new CostType();
		newType.setName(type.getName());
		
		return typeRepository.save(newType);
	}
	
	@Override
	public CostType update(final long id, final CostType type) {
		CostType currentType = typeRepository.findById(id)
				.orElseThrow(() -> new AppException("Cost type not found", HttpStatus.NOT_FOUND));
		currentType.setName(type.getName());
		
		return typeRepository.saveAndFlush(currentType);
	}
	
	@Override
	public Collection<CostType> findAll() {
		return typeRepository.findAll();
	}
	
	@Override
	public CostType findById(final long id) {
		return typeRepository.findById(id).orElse(null);
	}
	
	@Override
	public CostType delete(final long id) {
		CostType type = typeRepository.findById(id).orElse(null);
		if (type != null) {
			typeRepository.deleteById(type.getId());
		}
		
		return type;
	}
	
}
