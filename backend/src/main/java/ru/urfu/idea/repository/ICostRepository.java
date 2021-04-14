package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.entity.Cost;

@Repository
public interface ICostRepository extends JpaRepository<Cost, Long> {}
