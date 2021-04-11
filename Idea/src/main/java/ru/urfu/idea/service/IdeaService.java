package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.*;
import ru.urfu.idea.repository.ICostRepository;
import ru.urfu.idea.repository.ICostTypeRepository;
import ru.urfu.idea.repository.IIdeaRepository;
import ru.urfu.idea.repository.IIdeaStatusRepository;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class IdeaService implements IIdeaService {

	private final IIdeaRepository ideaRepository;
	private final IIdeaStatusRepository statusRepository;
	private final ICostTypeRepository costTypeRepository;
	
	@Override
	public Idea create(final Idea idea) {
		IdeaStatus status = statusRepository.findByName(IdeaStatusEnum.NEW.getName())
				.orElseThrow(() -> new RuntimeException("Status not found"));
		
		Idea newIdea = new Idea();
		newIdea.setName(idea.getName());
		newIdea.setText(idea.getText());
		newIdea.setCost(createCost(idea.getCost()));
		newIdea.setContact(createContact(idea.getContact()));
		newIdea.setAddress(idea.getAddress());
		newIdea.setStatus(status);
		
		return ideaRepository.save(newIdea);
	}
	
	private Cost createCost(final Cost cost) {
		CostType type = costTypeRepository.findByName(cost.getType().getName().getName())
				.orElseThrow(() -> new RuntimeException("Cost type not found"));
		
		Cost newCost = new Cost();
		
		newCost.setType(type);
		newCost.setValue(cost.getValue());
		newCost.setMinValue(cost.getMinValue());
		newCost.setMaxValue(cost.getMaxValue());
		
		return newCost;
	}
	
	private Contact createContact(final Contact contact) {
		Contact newContact = new Contact();
		
		newContact.setEmail(contact.getEmail());
		newContact.setPhoneNumber(contact.getPhoneNumber());
		
		return newContact;
	}
	
	@Override
	public Idea update(final long id, final Idea idea) {
		Idea currentIdea = ideaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Idea not found"));
		
		currentIdea.setName(idea.getName());
		currentIdea.setText(idea.getText());
		currentIdea.setCost(idea.getCost());
		currentIdea.setContact(idea.getContact());
		currentIdea.setAddress(idea.getAddress());
		
		return ideaRepository.saveAndFlush(currentIdea);
	}
	
	@Override
	public Collection<Idea> findAll() {
		return ideaRepository.findAll();
	}
	
	@Override
	public Idea findById(final long id) {
		return ideaRepository.findById(id).orElse(null);
	}
	
	@Override
	public Idea delete(final long id) {
		Idea idea = ideaRepository.findById(id).orElse(null);
		if (idea != null) {
			ideaRepository.deleteById(idea.getId());
		}
		
		return idea;
	}
	
}
