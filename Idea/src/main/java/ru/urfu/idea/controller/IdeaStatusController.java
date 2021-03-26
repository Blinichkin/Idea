package ru.urfu.idea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.model.IdeaStatus;
import ru.urfu.idea.service.IIdeaStatusService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ideaStatuses")
public class IdeaStatusController {
	
	@Autowired
	private final IIdeaStatusService service;
	
	public IdeaStatusController(IIdeaStatusService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<IdeaStatus> create(@RequestBody @Valid final IdeaStatus status)  {
		service.create(status);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<IdeaStatus> update(@PathVariable("id") final long id,
									   @RequestBody @Valid final IdeaStatus status) {
		IdeaStatus updatedStatus = service.update(id, status);
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
