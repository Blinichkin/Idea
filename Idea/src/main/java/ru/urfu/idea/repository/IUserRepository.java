package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {}
