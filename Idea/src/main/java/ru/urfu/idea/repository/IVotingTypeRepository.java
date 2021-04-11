package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.entity.VotingType;

import java.util.Optional;

@Repository
public interface IVotingTypeRepository extends JpaRepository<VotingType, Long> {
	
	Optional<VotingType> findByName(String name);
	
}
