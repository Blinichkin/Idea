package ru.urfu.idea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.urfu.idea.entity.Attachment;
import ru.urfu.idea.security.UserPrincipal;
import ru.urfu.idea.service.IAttachmentService;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/attachments")
public class AttachmentController {
	
	private final IAttachmentService attachmentService;
	
	@PostMapping(consumes = {"multipart/form-data"})
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Collection<Attachment>> create(@RequestPart("attachment") MultipartFile[] files) {
		Collection<Attachment> attachments = new ArrayList<>();
		for (MultipartFile file: files) {
			attachments.add(attachmentService.create(file));
		}
		
		return new ResponseEntity<>(attachments, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Collection<Attachment>> getAll() {
		Collection<Attachment> attachments = attachmentService.findAll();
		return new ResponseEntity<>(attachments, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Attachment> getById(@PathVariable("id") final long id) {
		Attachment attachment = attachmentService.findById(id);
		if (attachment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(attachment, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN') || (hasAuthority('USER') && #id == #userPrincipal.id)")
	public ResponseEntity<Attachment> delete(@AuthenticationPrincipal UserPrincipal userPrincipal,
											 @PathVariable("id") final long id) {
		Attachment attachment = attachmentService.delete(id);
		if (attachment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(attachment, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/download")
	public ResponseEntity<Resource> download(@PathVariable("id") final long id) {
		Attachment attachment = attachmentService.findById(id);
		if (attachment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Resource resource = attachmentService.download(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition
				.attachment()
				.filename(resource.getFilename())
				.build());
		headers.add("content-type", attachment.getContentType());
		
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
}
