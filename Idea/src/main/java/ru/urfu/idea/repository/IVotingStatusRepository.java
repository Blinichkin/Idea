package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.entity.VotingStatus;

import java.util.Optional;

@Repository
public interface IVotingStatusRepository extends JpaRepository<VotingStatus, Long> {
	
	Optional<VotingStatus> findByName(String name);
	
}
