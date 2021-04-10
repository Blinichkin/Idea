package ru.urfu.idea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.entity.IdeaStatus;
import ru.urfu.idea.mapper.IIdeaStatusMapper;
import ru.urfu.idea.mapper.request.IdeaStatusRequest;
import ru.urfu.idea.service.IIdeaStatusService;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/ideaStatuses")
public class IdeaStatusController {
	
	private final IIdeaStatusService statusService;
	private final IIdeaStatusMapper statusMapper;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<IdeaStatus> create(@RequestBody @Validated final IdeaStatusRequest statusRequest) {
		IdeaStatus status = statusMapper.requestToModel(statusRequest);
		IdeaStatus createdStatus = statusService.create(status);
		
		return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<IdeaStatus> update(@PathVariable("id") final long id,
											 @RequestBody @Validated final IdeaStatusRequest statusRequest) {
		IdeaStatus status = statusMapper.requestToModel(statusRequest);
		IdeaStatus updatedStatus = statusService.update(id, status);
		
		return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<IdeaStatus>> getAll() {
		Collection<IdeaStatus> statuses = statusService.findAll();
		return new ResponseEntity<>(statuses, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<IdeaStatus> getById(@PathVariable("id") final long id) {
		IdeaStatus status = statusService.findById(id);
		if (status == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<IdeaStatus> delete(@PathVariable("id") final long id) {
		IdeaStatus status = statusService.delete(id);
		if (status == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
