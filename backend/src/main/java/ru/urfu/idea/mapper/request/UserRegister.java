package ru.urfu.idea.mapper.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegister implements Serializable {
	
	private String login;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String patronymic;
	
}
