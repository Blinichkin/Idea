package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.entity.Idea;

import java.util.Collection;

@Repository
public interface IIdeaRepository extends JpaRepository<Idea, Long> {
	
	@Query("SELECT i FROM Idea i WHERE :id = i.status.id")
	Collection<Idea> findAllByStatus(long id);
	
}
