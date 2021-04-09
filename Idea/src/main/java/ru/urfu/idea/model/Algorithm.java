package ru.urfu.idea.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "algorithms")
public class Algorithm implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	public EncryptionAlgorithm getName() {
		if (name == null) {
			return null;
		}
		
		for (EncryptionAlgorithm algorithm: EncryptionAlgorithm.values()) {
			if (name.equals(algorithm.getName()))
				return algorithm;
		}
		
		throw new IllegalArgumentException("No such value");
	}
	
	public void setName(EncryptionAlgorithm algorithm) {
		if (algorithm == null) {
			name = null;
		}
		
		name = algorithm.getName();
	}
	
}
