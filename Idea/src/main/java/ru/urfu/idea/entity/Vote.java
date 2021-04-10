package ru.urfu.idea.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "votes")
@EntityListeners(AuditingEntityListener.class)
public class Vote implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "voting_id", nullable = false)
	private Voting voting;
	
	@Column(name = "option_answer", nullable = false)
	private byte optionAnswer;
	
	@CreatedDate
	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;
	
	@CreatedBy
	@ManyToOne
	@JoinColumn(name = "created_by_id", nullable = false)
	private User createdBy;
	
}
