package ru.urfu.idea.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public enum EncryptionAlgorithm implements Serializable {
	
	BCRYPT("BCRYPT"),
	SCRYPT("SCRYPT");
	
	private final String name;
	
}
