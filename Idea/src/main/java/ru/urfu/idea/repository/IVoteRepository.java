package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.entity.Vote;

@Repository
public interface IVoteRepository extends JpaRepository<Vote, Long> {}
