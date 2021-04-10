package ru.urfu.idea.mapper.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IdeaRequest implements Serializable {
	
	private String name;
	
	private String text;
	
	private ContactRequest contact;
	
}
