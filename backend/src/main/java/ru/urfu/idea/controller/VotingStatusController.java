package ru.urfu.idea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.entity.VotingStatus;
import ru.urfu.idea.mapper.IVotingStatusMapper;
import ru.urfu.idea.mapper.request.VotingStatusRequest;
import ru.urfu.idea.service.IVotingStatusService;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/votingStatuses")
public class VotingStatusController {
	
	private final IVotingStatusService votingStatusService;
	private final IVotingStatusMapper votingStatusMapper;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<VotingStatus> create(@RequestBody @Validated final VotingStatusRequest statusRequest) {
		VotingStatus status = votingStatusMapper.requestToModel(statusRequest);
		VotingStatus createdStatus = votingStatusService.create(status);
		
		return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<VotingStatus> update(@PathVariable("id") final long id,
											   @RequestBody @Validated final VotingStatusRequest statusRequest) {
		VotingStatus status = votingStatusMapper.requestToModel(statusRequest);
		VotingStatus updatedStatus = votingStatusService.update(id, status);
		
		return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<VotingStatus>> getAll() {
		Collection<VotingStatus> statuses = votingStatusService.findAll();
		return new ResponseEntity<>(statuses, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VotingStatus> getById(@PathVariable("id") final long id) {
		VotingStatus status = votingStatusService.findById(id);
		if (status == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<VotingStatus> delete(@PathVariable("id") final long id) {
		VotingStatus status = votingStatusService.delete(id);
		if (status == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
