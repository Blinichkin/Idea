package ru.urfu.idea.mapper.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.urfu.idea.entity.VotingStatus;
import ru.urfu.idea.entity.VotingType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VotingResponse implements Serializable {
	
	private long id;
	
	private int requiredVotes;
	
	private VotingType type;
	
	private VotingStatus status;
	
	private LocalDateTime createdDate;
	
	private UserResponse createdBy;
	
	private Collection<VoteResponse> votes;
	
}
