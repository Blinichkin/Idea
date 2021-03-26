package ru.urfu.idea.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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
		
		return IdeaStatusEnum.valueOf(name);
	}
	
	public void setName(IdeaStatusEnum ideaStatusEnum) {
		if (ideaStatusEnum == null) {
			name = null;
		}
		
		name = ideaStatusEnum.getName();
	}
	
}
