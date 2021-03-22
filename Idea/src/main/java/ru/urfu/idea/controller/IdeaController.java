package ru.urfu.idea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.model.Idea;
import ru.urfu.idea.service.IIdeaService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ideas")
public class IdeaController {
	
	@Autowired
	private final IIdeaService service;
	
	public IdeaController(IIdeaService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Idea> create(@RequestBody @Valid final Idea idea)  {
		service.create(idea);
		return new ResponseEntity<>(idea, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Idea> update(@PathVariable("id") final long id,
									   @RequestBody @Valid final Idea idea) {
		Idea updatedIdea = service.update(id, idea);
		return new ResponseEntity<>(updatedIdea, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Idea>> getAll() {
		List<Idea> ideas = service.findAll();
		return new ResponseEntity<>(ideas, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Idea> getById(@PathVariable("id") final long id) {
		Idea idea = service.findById(id);
		
		if (idea == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(idea, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Idea> delete(@PathVariable("id") final long id) {
		Idea idea = service.findById(id);
		
		if (idea == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}