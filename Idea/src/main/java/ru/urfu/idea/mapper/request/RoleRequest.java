package ru.urfu.idea.mapper.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.urfu.idea.entity.RoleEnum;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleRequest implements Serializable {
	
	private String name;
	
	private String permission;
	
	public RoleEnum getName() {
		if (name == null) {
			return null;
		}
		
		return RoleEnum.valueOf(name);
	}
	
	public void setName(RoleEnum role) {
		if (role == null) {
			name = null;
		} else {
			name = role.getName();
		}
	}
	
}
