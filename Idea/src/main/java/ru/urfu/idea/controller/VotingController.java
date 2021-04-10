package ru.urfu.idea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.entity.Voting;
import ru.urfu.idea.mapper.IVotingMapper;
import ru.urfu.idea.mapper.request.VotingRequest;
import ru.urfu.idea.mapper.response.VotingResponse;
import ru.urfu.idea.service.IVotingService;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/voting")
public class VotingController {
	
	private final IVotingService votingService;
	private final IVotingMapper votingMapper;
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
	public ResponseEntity<VotingResponse> create(@RequestBody @Validated final VotingRequest votingRequest) {
		Voting voting = votingMapper.requestToModel(votingRequest);
		Voting createdVoting = votingService.create(votingRequest.getIdeaId(), voting);
		VotingResponse votingResponse = votingMapper.modelToResponse(createdVoting);
		
		return new ResponseEntity<>(votingResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
	public ResponseEntity<VotingResponse> update(@PathVariable("id") final long id,
												 @RequestBody @Validated final Voting voting) {
		Voting updatedVoting = votingService.update(id, voting);
		VotingResponse votingResponse = votingMapper.modelToResponse(updatedVoting);
		
		return new ResponseEntity<>(votingResponse, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<VotingResponse>> getAll() {
		Collection<Voting> voting = votingService.findAll();
		Collection<VotingResponse> votingResponses = votingMapper.modelToResponse(voting);
		
		return new ResponseEntity<>(votingResponses, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VotingResponse> getById(@PathVariable("id") final long id) {
		Voting voting = votingService.findById(id);
		
		if (voting == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		VotingResponse votingResponse = votingMapper.modelToResponse(voting);
		
		return new ResponseEntity<>(votingResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('MODER', 'ADMIN')")
	public ResponseEntity<Voting> delete(@PathVariable("id") final long id) {
		votingService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
