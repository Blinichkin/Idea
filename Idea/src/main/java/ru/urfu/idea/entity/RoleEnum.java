package ru.urfu.idea.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public enum RoleEnum implements Serializable {

	USER("USER"),
	EXPERT("EXPERT"),
	MODER("MODER"),
	ADMIN("ADMIN");
	
	private final String name;

}
