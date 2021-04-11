package ru.urfu.idea.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public enum VotingTypeEnum implements Serializable {
	
	STUDENT("STUDENT"),
	EXPERT("EXPERT");
	
	private final String name;
	
}
