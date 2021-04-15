package ru.urfu.idea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.entity.CostType;
import ru.urfu.idea.entity.VotingType;
import ru.urfu.idea.mapper.IVotingTypeMapper;
import ru.urfu.idea.mapper.request.CostTypeRequest;
import ru.urfu.idea.mapper.request.VotingTypeRequest;
import ru.urfu.idea.service.IVotingTypeService;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/votingTypes")
public class VotingTypeController {
	
	private final IVotingTypeService votingTypeService;
	private final IVotingTypeMapper votingTypeMapper;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<VotingType> create(@RequestBody @Validated final VotingTypeRequest typeRequest) {
		VotingType type = votingTypeMapper.requestToModel(typeRequest);
		VotingType createdType = votingTypeService.create(type);
		
		return new ResponseEntity<>(createdType, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<VotingType> update(@PathVariable("id") final long id,
										   @RequestBody @Validated final VotingTypeRequest typeRequest) {
		VotingType type = votingTypeMapper.requestToModel(typeRequest);
		VotingType updatedType = votingTypeService.update(id, type);
		
		return new ResponseEntity<>(updatedType, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<VotingType>> getAll() {
		Collection<VotingType> types = votingTypeService.findAll();
		return new ResponseEntity<>(types, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VotingType> getById(@PathVariable("id") final long id) {
		VotingType type = votingTypeService.findById(id);
		if (type == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(type, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<VotingType> delete(@PathVariable("id") final long id) {
		VotingType costType = votingTypeService.delete(id);
		if (costType == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
