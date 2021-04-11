package ru.urfu.idea.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public enum CostTypeEnum implements Serializable {
	
	UNKNOWN("UNKNOWN"),
	EXACT("EXACT"),
	RANGE("RANGE");
	
	private final String name;
	
}
