package ru.urfu.idea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.entity.IdeaStatus;
import ru.urfu.idea.mapper.request.IdeaStatusRequest;
import ru.urfu.idea.service.IIdeaStatusService;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/ideaStatuses")
public class IdeaStatusController {
	
	private final IIdeaStatusService service;
	
	@PostMapping
	public ResponseEntity<IdeaStatus> create(
			@RequestBody @Validated final IdeaStatusRequest statusRequest)  {
		IdeaStatus createdStatus = service.create(statusRequest);
		return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<IdeaStatus> update(@PathVariable("id") final long id,
									   @RequestBody @Validated final IdeaStatusRequest statusRequest) {
		IdeaStatus updatedStatus = service.update(id, statusRequest);
		return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<IdeaStatus>> getAll() {
		List<IdeaStatus> statuses = service.findAll();
		return new ResponseEntity<>(statuses, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<IdeaStatus> getById(@PathVariable("id") final long id) {
		IdeaStatus status = service.findById(id);
		
		if (status == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<IdeaStatus> delete(@PathVariable("id") final long id) {
		IdeaStatus status = service.findById(id);
		
		if (status == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
