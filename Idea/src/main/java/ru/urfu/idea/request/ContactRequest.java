package ru.urfu.idea.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactRequest implements Serializable {
	
	private String phoneNumber;
	
	private String email;
	
}
