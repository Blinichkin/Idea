package ru.urfu.idea.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "attachments")
@EntityListeners(AuditingEntityListener.class)
public class Attachment implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@CreatedDate
	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;
	
	@ManyToOne
	@JoinColumn(name = "idea_id", nullable = false)
	private Idea idea;
	
}
