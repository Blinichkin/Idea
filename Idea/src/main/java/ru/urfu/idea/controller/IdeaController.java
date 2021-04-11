package ru.urfu.idea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.urfu.idea.entity.Idea;
import ru.urfu.idea.mapper.IIdeaMapper;
import ru.urfu.idea.mapper.request.IdeaRequest;
import ru.urfu.idea.mapper.response.IdeaResponse;
import ru.urfu.idea.security.UserPrincipal;
import ru.urfu.idea.service.IAttachmentService;
import ru.urfu.idea.service.IContactService;
import ru.urfu.idea.service.ICostService;
import ru.urfu.idea.service.IIdeaService;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/ideas")
public class IdeaController {
	
	private final IIdeaService ideaService;
	private final ICostService costService;
	private final IContactService contactService;
	private final IAttachmentService attachmentService;
	private final IIdeaMapper ideaMapper;
	
	@PostMapping(consumes = {"multipart/form-data"})
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<IdeaResponse> create(@RequestPart("idea") @Validated final IdeaRequest ideaRequest,
											   @RequestPart("attachment") MultipartFile[] files) {
		Idea idea = ideaMapper.requestToModel(ideaRequest);
		idea.setCost(costService.create(idea.getCost()));
		idea.setContact(contactService.create(idea.getContact()));
		Idea createdIdea = ideaService.create(idea);
		for (MultipartFile file: files) {
			attachmentService.create(createdIdea.getId(), file);
		}
		IdeaResponse ideaResponse = ideaMapper.modelToResponse(createdIdea);
		
		return new ResponseEntity<>(ideaResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODER') || (hasAuthority('USER') && #id == #userPrincipal.id)")
	public ResponseEntity<IdeaResponse> update(@AuthenticationPrincipal UserPrincipal userPrincipal,
											   @PathVariable("id") final long id,
											   @RequestBody @Validated final IdeaRequest ideaRequest) {
		Idea idea = ideaMapper.requestToModel(ideaRequest);
		Idea updatedIdea = ideaService.update(id, idea);
		IdeaResponse ideaResponse = ideaMapper.modelToResponse(updatedIdea);
		
		return new ResponseEntity<>(ideaResponse, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<IdeaResponse>> getAll() {
		Collection<Idea> ideas = ideaService.findAll();
		Collection<IdeaResponse> ideaResponses = ideaMapper.modelToResponse(ideas);
		
		return new ResponseEntity<>(ideaResponses, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<IdeaResponse> getById(@PathVariable("id") final long id) {
		Idea idea = ideaService.findById(id);
		if (idea == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		IdeaResponse ideaResponse = ideaMapper.modelToResponse(idea);
		
		return new ResponseEntity<>(ideaResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN') || (hasAuthority('USER') && #id == #userPrincipal.id)")
	public ResponseEntity<IdeaResponse> delete(@AuthenticationPrincipal UserPrincipal userPrincipal,
											   @PathVariable("id") final long id) {
		Idea idea = ideaService.delete(id);
		if (idea == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		IdeaResponse ideaResponse = ideaMapper.modelToResponse(idea);
		
		return new ResponseEntity<>(ideaResponse, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/submit")
	public ResponseEntity<IdeaResponse> submit(@PathVariable("id") final long id) {
		Idea idea = ideaService.submit(id);
		IdeaResponse ideaResponse = ideaMapper.modelToResponse(idea);
		
		return new ResponseEntity<>(ideaResponse, HttpStatus.OK);
	}
	
	@GetMapping("/GET/{status}")
	public ResponseEntity<Collection<IdeaResponse>> getAllByStatusName(@PathVariable("status") final String status) {
		Collection<Idea> ideas = ideaService.findAllByStatusName(status);
		Collection<IdeaResponse> ideaResponses = ideaMapper.modelToResponse(ideas);
		
		return new ResponseEntity<>(ideaResponses, HttpStatus.OK);
	}

}
