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
@Table(name = "ideas")
@EntityListeners(AuditingEntityListener.class)
public class Idea implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "text", nullable = false)
	private String text;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "contact_id", nullable = false)
	private Contact contact;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "status_id", nullable = false)
	private IdeaStatus status;
	
	@OneToMany(mappedBy = "idea", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Attachment> attachments;
	
	@CreatedDate
	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;
	
	@CreatedBy
	@ManyToOne
	@JoinColumn(name = "created_by_id", nullable = false)
	private User createdBy;
	
	@OneToMany(mappedBy = "idea", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Voting> voting;

}
