package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.urfu.idea.entity.Attachment;
import ru.urfu.idea.entity.Idea;
import ru.urfu.idea.repository.IAttachmentRepository;
import ru.urfu.idea.repository.IIdeaRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class AttachmentService implements IAttachmentService {
	
	private final IAttachmentRepository attachmentRepository;
	private final IIdeaRepository ideaRepository;
	
	@Override
	public Attachment create(final long ideaId, final MultipartFile multipartFile) {
		String filename = multipartFile.getOriginalFilename();
		Idea idea = ideaRepository.findById(ideaId)
				.orElseThrow(() -> new RuntimeException("Idea not found"));
		
		checkDirectories(ideaId);
		createFile(multipartFile, "files/" + ideaId + "/" + filename);
		
		Attachment attachment = new Attachment();
		attachment.setName(filename);
		attachment.setIdea(idea);
		
		return attachmentRepository.save(attachment);
	}
	
	private void checkDirectories(final long ideaId) {
		try {
			if (!Files.exists(Paths.get("files"))) {
				Files.createDirectory(Paths.get("files"));
			}
			
			if (!Files.exists(Paths.get("files" + "/" + ideaId))) {
				Files.createDirectory(Paths.get("files" + "/" + ideaId));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createFile(final MultipartFile multipartFile, final String path) {
		try {
			Files.copy(multipartFile.getInputStream(), Paths.get(path));
		} catch (IOException e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}
	
	@Override
	public Attachment update(final long id, final Attachment attachment) {
		return null;
	}
	
	@Override
	public Collection<Attachment> findAll() {
		return attachmentRepository.findAll();
	}
	
	@Override
	public Attachment findById(long id) {
		return attachmentRepository.findById(id).orElse(null);
	}
	
	@Override
	public Attachment delete(long id) {
		Attachment attachment = attachmentRepository.findById(id).orElse(null);
		if (attachment != null) {
			attachmentRepository.deleteById(id);
			deleteFile("files/" + attachment.getIdea().getId() + "/" + attachment.getName());
		}
		
		return attachment;
	}
	
	private void deleteFile(String path) {
		try {
			Files.delete(Paths.get(path));
		} catch (IOException e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}
	
}
