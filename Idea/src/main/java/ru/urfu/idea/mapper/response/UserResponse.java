package ru.urfu.idea.mapper.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse implements Serializable {
	
	private long id;
	
	private String login;
	
	private String lastName;
	
	private String firstName;
	
	private String patronymic;
	
}
