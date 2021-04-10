package ru.urfu.idea.mapper.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoteResponse implements Serializable {
	
	private long id;
	
	private byte optionAnswer;
	
	private LocalDateTime createdDate;
	
}
