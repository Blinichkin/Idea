package ru.urfu.idea.mapper.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.urfu.idea.entity.CostTypeEnum;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CostTypeRequest implements Serializable {
	
	private String name;
	
	public CostTypeEnum getName() {
		if (name == null) {
			return null;
		}
		
		return CostTypeEnum.valueOf(name);
	}
	
	public void setName(CostTypeEnum type) {
		if (type == null) {
			name = null;
		} else {
			name = type.getName();
		}
	}
	
}
