package ru.urfu.idea.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public enum IdeaStatusEnum implements Serializable {
	
	NEW("NEW"),
	MODERATION("Модерация"),
	VOTING_STUDENT("Голосование (студенческий состав)"),
	VOTING_EXPERT("Голосование (экспертный состав)"),
	IN_WORK("Осуществление работ"),
	COMPLETED("Выполнена");
	
	private final String name;
	
}
