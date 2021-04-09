package ru.urfu.idea.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "idea_statuses")
public class IdeaStatus implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	public IdeaStatusEnum getName() {
		if (name == null) {
			return null;
		}
		
		for (IdeaStatusEnum status: IdeaStatusEnum.values()) {
			if (name.equals(status.getName()))
				return status;
		}
		
		throw new IllegalArgumentException("No such value");
	}
	
	public void setName(IdeaStatusEnum ideaStatusEnum) {
		if (ideaStatusEnum == null) {
			name = null;
		}
		
		name = ideaStatusEnum.getName();
	}
	
}
