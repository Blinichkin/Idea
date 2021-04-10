package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.entity.Idea;

@Repository
public interface IIdeaRepository extends JpaRepository<Idea, Long> {}
