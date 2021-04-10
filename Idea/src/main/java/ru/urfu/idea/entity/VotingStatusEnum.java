package ru.urfu.idea.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public enum VotingStatusEnum implements Serializable {
	
	ACTIVE("ACTIVE"),
	COMPLETED("COMPLETED");
	
	private final String name;
	
}
