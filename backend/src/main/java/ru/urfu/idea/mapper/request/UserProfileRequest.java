package ru.urfu.idea.mapper.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserProfileRequest implements Serializable {
	
	private String lastName;
	
	private String firstName;
	
	private String patronymic;
	
}
