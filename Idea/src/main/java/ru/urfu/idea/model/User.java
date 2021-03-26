package ru.urfu.idea.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true, nullable = false)
	private String login;
	
	@Column(nullable = false)
	private String password;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
	
	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	private Collection<Idea> ideas;

}
