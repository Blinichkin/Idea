package ru.urfu.idea.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Role  implements GrantedAuthority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	public RoleEnum getName() {
		if (name == null) {
			return null;
		}
		
		for (RoleEnum role: RoleEnum.values()) {
			if (name.equals(role.getName()))
				return role;
		}
		
		throw new IllegalArgumentException("No such value");
	}
	
	public void setName(RoleEnum roleEnum) {
		name = roleEnum.getName();
	}
	
	@Override
	public String getAuthority() {
		return name;
	}
	
}
