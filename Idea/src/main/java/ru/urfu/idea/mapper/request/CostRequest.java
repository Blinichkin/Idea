package ru.urfu.idea.mapper.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CostRequest implements Serializable {
	
	private CostTypeRequest type;
	
	private BigDecimal value;
	
	private BigDecimal minValue;
	
	private BigDecimal maxValue;
	
}
