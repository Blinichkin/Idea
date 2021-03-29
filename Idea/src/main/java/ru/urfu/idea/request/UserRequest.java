package ru.urfu.idea.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest implements Serializable {
	
	private String login;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String patronymic;
	
}
