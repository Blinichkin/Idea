package ru.urfu.idea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.urfu.idea.entity.Attachment;
import ru.urfu.idea.repository.IAttachmentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class AttachmentService implements IAttachmentService {
	
	private final IAttachmentRepository attachmentRepository;
	
	@Value("${project.upload.path}")
	private String uploadPath;
	
	@Override
	public Attachment create(final MultipartFile file) {
		String filename = UUID.randomUUID() + "." + file.getOriginalFilename();
		
		checkDirectories(uploadPath);
		createFile(file, uploadPath + "/" + filename);
		
		Attachment attachment = new Attachment();
		attachment.setName(filename);
		attachment.setContentType(file.getContentType());
		
		return attachmentRepository.save(attachment);
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
			deleteFile(uploadPath + "/" + attachment.getName());
		}
		
		return attachment;
	}
	
	@Override
	public Resource download(long id) {
		Attachment attachment = attachmentRepository.findById(id).orElse(null);
		if (attachment == null) {
			return null;
		}
		
		return openFile(uploadPath + "/" + attachment.getName());
	}
	
	private static void checkDirectories(final String name) {
		try {
			if (!Files.exists(Paths.get(name))) {
				Files.createDirectory(Paths.get(name));
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
	
	private Resource openFile(final String path) {
		try {
			return new UrlResource(Paths.get(path).toUri());
		} catch (IOException e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}
	
	private void deleteFile(final String path) {
		try {
			Files.delete(Paths.get(path));
		} catch (IOException e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}
	
}
