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
	
	private CostRequest cost;
	
	private ContactRequest contact;
	
	private String address;
	
}
