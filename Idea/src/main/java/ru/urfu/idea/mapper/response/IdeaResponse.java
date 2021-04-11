package ru.urfu.idea.mapper.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.urfu.idea.entity.Contact;
import ru.urfu.idea.entity.Cost;
import ru.urfu.idea.entity.IdeaStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IdeaResponse implements Serializable {
	
	private long id;
	
	private String name;
	
	private String text;
	
	private Cost cost;

	private Contact contact;
	
	private String address;
	
	private IdeaStatus status;
	
	private LocalDateTime createdDate;

	private UserResponse createdBy;
	
}