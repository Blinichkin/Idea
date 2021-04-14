package ru.urfu.idea.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cost")
public class Cost implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "type_id", nullable = false)
	private CostType type;
	
	@Column(name = "value")
	private BigDecimal value;
	
	@Column(name = "min_value")
	private BigDecimal minValue;
	
	@Column(name = "max_value")
	private BigDecimal maxValue;
	
}
