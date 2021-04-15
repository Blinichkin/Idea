package ru.urfu.idea.mapper.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.urfu.idea.entity.VotingStatusEnum;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VotingStatusRequest implements Serializable {
	
	private String name;
	
	public VotingStatusEnum getName() {
		if (name == null) {
			return null;
		}
		
		return VotingStatusEnum.valueOf(name);
	}
	
	public void setName(VotingStatusEnum status) {
		if (status == null) {
			name = null;
		} else {
			name = status.getName();
		}
	}
	
}
