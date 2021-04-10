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
	public Attachment create(long ideaId, MultipartFile multipartFile) {
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
	
	private void checkDirectories(long ideaId) {
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
	
	private void createFile(MultipartFile multipartFile, String path) {
		try {
			Files.copy(multipartFile.getInputStream(), Paths.get(path));
		} catch (IOException e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}
	
	@Override
	public Attachment update(long id, Attachment attachment) {
		return null;
	}
	
	@Override
	public Collection<Attachment> findAll() {
		return null;
	}
	
	@Override
	public Attachment findById(long id) {
		return null;
	}
	
	@Override
	public void delete(long id) {
	
	}
	
}
