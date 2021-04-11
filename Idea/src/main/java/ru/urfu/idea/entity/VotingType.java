package ru.urfu.idea.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "voting_types")
public class VotingType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	public VotingTypeEnum getName() {
		if (name == null) {
			return null;
		}
		
		for (VotingTypeEnum type: VotingTypeEnum.values()) {
			if (name.equals(type.getName()))
				return type;
		}
		
		throw new IllegalArgumentException("No such value");
	}
	
	public void setName(VotingTypeEnum type) {
		name = type.getName();
	}
	
}
