package ru.urfu.idea.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ru.urfu.idea.entity.Attachment;

import java.util.Collection;

public interface IAttachmentService {
	
	Attachment create(MultipartFile multipartFile);
	
	Collection<Attachment> findAll();
	
	Attachment findById(long id);
	
	Attachment delete(long id);
	
	Resource download(long id);
	
}
