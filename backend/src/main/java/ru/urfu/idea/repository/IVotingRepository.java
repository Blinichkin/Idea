package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.entity.Voting;

@Repository
public interface IVotingRepository extends JpaRepository<Voting, Long> {}
