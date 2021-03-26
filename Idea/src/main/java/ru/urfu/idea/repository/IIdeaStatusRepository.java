package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.model.IdeaStatus;

@Repository
public interface IIdeaStatusRepository extends JpaRepository<IdeaStatus, Long> {}
