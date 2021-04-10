package ru.urfu.idea.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.urfu.idea.entity.Role;

import java.io.Serializable;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserPrincipal implements Serializable {
	
	private long id;
	
	private String login;
	
	private String password;
	
	private Collection<Role> roles;
	
}
