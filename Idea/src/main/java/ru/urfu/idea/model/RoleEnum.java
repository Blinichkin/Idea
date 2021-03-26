package ru.urfu.idea.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public enum RoleEnum implements Serializable {

	USER("USER"),
	EXPERT("EXPERT"),
	MODERATOR("MODERATOR"),
	ADMIN("ADMIN");
	
	private final String name;

}
