package ru.urfu.idea.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cost_types")
public class CostType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	public CostTypeEnum getName() {
		if (name == null) {
			return null;
		}
		
		for (CostTypeEnum type: CostTypeEnum.values()) {
			if (name.equals(type.getName()))
				return type;
		}
		
		throw new IllegalArgumentException("No such value");
	}
	
	public void setName(CostTypeEnum type) {
		name = type.getName();
	}
	
}
