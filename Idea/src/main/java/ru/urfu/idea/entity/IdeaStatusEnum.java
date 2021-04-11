package ru.urfu.idea.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public enum IdeaStatusEnum implements Serializable {
	
	NEW("NEW"),
	MODERATION("MODERATION"),
	VOTING_STUDENT("VOTING_STUDENT"),
	VOTING_EXPERT("VOTING_EXPERT"),
	IN_WORK("IN_WORK"),
	COMPLETED("COMPLETED");
	
	private final String name;
	
}
