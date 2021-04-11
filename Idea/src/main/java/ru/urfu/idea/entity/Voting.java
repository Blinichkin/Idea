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
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "voting")
@EntityListeners(AuditingEntityListener.class)
public class Voting implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "idea_id", nullable = false)
	private Idea idea;
	
	@Column(name = "required_votes", nullable = false)
	private int requiredVotes;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "type_id", nullable = false)
	private VotingType type;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "status_id", nullable = false)
	private VotingStatus status;
	
	@CreatedDate
	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;
	
	@CreatedBy
	@ManyToOne
	@JoinColumn(name = "created_by_id", nullable = false)
	private User createdBy;
	
	@OneToMany(mappedBy = "voting", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Vote> votes;
	
}
