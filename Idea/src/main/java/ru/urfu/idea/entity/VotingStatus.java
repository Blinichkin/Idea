package ru.urfu.idea.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "voting_statuses")
public class VotingStatus implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	public VotingStatusEnum getName() {
		if (name == null) {
			return null;
		}
		
		for (VotingStatusEnum status: VotingStatusEnum.values()) {
			if (name.equals(status.getName()))
				return status;
		}
		
		throw new IllegalArgumentException("No such value");
	}
	
	public void setName(VotingStatusEnum status) {
		name = status.getName();
	}
	
}
