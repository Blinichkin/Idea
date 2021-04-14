package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.entity.Vote;

@Repository
public interface IVoteRepository extends JpaRepository<Vote, Long> {
	
	@Query("SELECT COUNT(v) FROM Vote v WHERE :id = v.voting.id")
	Long getVotesCount(long id);
	
	@Query("SELECT COUNT(v) FROM Vote v WHERE :id = v.voting.id AND v.optionAnswer = true")
	Long getVotesFor(long id);
	
	@Query("SELECT COUNT(v) FROM Vote v WHERE :id = v.voting.id AND v.optionAnswer = false")
	Long getVotesAgainst(long id);
	
}
