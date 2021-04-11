package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.Cost;
import ru.urfu.idea.entity.CostType;
import ru.urfu.idea.repository.ICostRepository;
import ru.urfu.idea.repository.ICostTypeRepository;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CostService implements ICostService {
	
	private final ICostRepository costRepository;
	private final ICostTypeRepository costTypeRepository;
	
	@Override
	public Cost create(final Cost cost) {
		CostType type = costTypeRepository.findByName(cost.getType().getName().getName())
				.orElseThrow(() -> new RuntimeException("Cost type not found"));
		
		checkCost(type, cost);
		Cost newCost = new Cost();
		newCost.setType(type);
		
		switch (type.getName()) {
			case EXACT:
				newCost.setValue(cost.getValue());
				break;
			case RANGE:
				newCost.setMinValue(cost.getMinValue());
				newCost.setMaxValue(cost.getMaxValue());
				break;
		}
		
		return costRepository.save(newCost);
	}
	
	private void checkCost(CostType type, Cost cost) {
		switch (type.getName()) {
			case EXACT:
				if (cost.getValue() == null) {
					throw new RuntimeException("Cost value not found");
				}
				break;
			case RANGE:
				if (cost.getMinValue() == null) {
					throw new RuntimeException("Cost minValue not found");
				}
				if (cost.getMaxValue() == null) {
					throw new RuntimeException("Cost maxValue not found");
				}
				break;
		}
	}
	
	@Override
	public Cost update(final long id, final Cost cost) {
		Cost currentCost = costRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cost not found"));
		
		currentCost.setType(cost.getType());
		switch (cost.getType().getName()) {
			case UNKNOWN:
				currentCost.setValue(null);
				currentCost.setMinValue(null);
				currentCost.setMaxValue(null);
				break;
			case EXACT:
				currentCost.setValue(cost.getValue());
				currentCost.setMinValue(null);
				currentCost.setMaxValue(null);
				break;
			case RANGE:
				currentCost.setValue(null);
				currentCost.setMinValue(cost.getMinValue());
				currentCost.setMaxValue(cost.getMaxValue());
				break;
			default:
				throw new RuntimeException("Cost type not found");
		}
		
		return costRepository.saveAndFlush(currentCost);
	}
	
	@Override
	public Collection<Cost> findAll() {
		return costRepository.findAll();
	}
	
	@Override
	public Cost findById(final long id) {
		return costRepository.findById(id).orElse(null);
	}
	
	@Override
	public Cost delete(final long id) {
		Cost cost = costRepository.findById(id).orElse(null);
		if (cost != null) {
			costRepository.deleteById(cost.getId());
		}
		
		return cost;
	}
	
}
