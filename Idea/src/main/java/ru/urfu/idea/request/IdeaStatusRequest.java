package ru.urfu.idea.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.urfu.idea.model.IdeaStatusEnum;

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
