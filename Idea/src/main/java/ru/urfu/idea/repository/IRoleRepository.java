package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {}