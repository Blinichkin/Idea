package ru.urfu.idea.service;

import org.springframework.web.multipart.MultipartFile;
import ru.urfu.idea.entity.Attachment;

import java.util.Collection;

public interface IAttachmentService {
	
	Attachment create(long ideaId, MultipartFile multipartFile);
	
	Attachment update(long id, Attachment attachment);
	
	Collection<Attachment> findAll();
	
	Attachment findById(long id);
	
	Attachment delete(long id);
	
}
