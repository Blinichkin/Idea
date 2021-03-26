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
@Table(name = "roles")
public class Role implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String permission;
	
	public RoleEnum getName() {
		if (name == null) {
			return null;
		}
		
		return RoleEnum.valueOf(name);
	}
	
	public void setName(RoleEnum roleEnum) {
		name = roleEnum.getName();
	}

}
