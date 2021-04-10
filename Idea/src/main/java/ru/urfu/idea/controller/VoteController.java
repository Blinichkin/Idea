package ru.urfu.idea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.entity.Vote;
import ru.urfu.idea.mapper.IVoteMapper;
import ru.urfu.idea.mapper.request.VoteRequest;
import ru.urfu.idea.mapper.response.VoteResponse;
import ru.urfu.idea.service.IVoteService;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/votes")
public class VoteController {
	
	private final IVoteService voteService;
	private final IVoteMapper voteMapper;
	
	@PostMapping
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<VoteResponse> create(@RequestBody @Validated final VoteRequest voteRequest) {
		Vote vote = voteMapper.requestToModel(voteRequest);
		Vote createdVote = voteService.create(voteRequest.getVotingId(), vote);
		VoteResponse voteResponse = voteMapper.modelToResponse(createdVote);
		
		return new ResponseEntity<>(voteResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<VoteResponse> update(@PathVariable("id") final long id,
											   @RequestBody @Validated final Vote vote) {
		Vote updatedVote = voteService.update(id, vote);
		VoteResponse voteResponse = voteMapper.modelToResponse(updatedVote);
		
		return new ResponseEntity<>(voteResponse, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<VoteResponse>> getAll() {
		Collection<Vote> votes = voteService.findAll();
		Collection<VoteResponse> voteResponses = voteMapper.modelToResponse(votes);
		
		return new ResponseEntity<>(voteResponses, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VoteResponse> getById(@PathVariable("id") final long id) {
		Vote vote = voteService.findById(id);
		
		if (vote == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		VoteResponse voteResponse = voteMapper.modelToResponse(vote);
		
		return new ResponseEntity<>(voteResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Vote> delete(@PathVariable("id") final long id) {
		voteService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
