package ru.urfu.idea.mapper.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.urfu.idea.entity.IdeaStatusEnum;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IdeaStatusRequest implements Serializable {
	
	private String name;
	
	public IdeaStatusEnum getName() {
		if (name == null) {
			return null;
		}
		
		return IdeaStatusEnum.valueOf(name);
	}
	
	public void setName(IdeaStatusEnum status) {
		if (status == null) {
			name = null;
		} else {
			name = status.getName();
		}
	}
	
}
