package ru.urfu.idea.mapper.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VotingRequest implements Serializable {
	
	private long ideaId;
	
	private int requiredVotes;
	
	private VotingTypeRequest type;
	
}
