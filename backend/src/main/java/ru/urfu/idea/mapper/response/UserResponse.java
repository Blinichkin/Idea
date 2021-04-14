package ru.urfu.idea.mapper.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.urfu.idea.entity.Role;

import java.io.Serializable;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse implements Serializable {
	
	private long id;
	
	private String login;
	
	private String lastName;
	
	private String firstName;
	
	private String patronymic;
	
	private Collection<Role> roles;
	
}
