package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.entity.IdeaStatus;

import java.util.Optional;

@Repository
public interface IIdeaStatusRepository extends JpaRepository<IdeaStatus, Long> {
	
	Optional<IdeaStatus> findByName(String name);
	
}
