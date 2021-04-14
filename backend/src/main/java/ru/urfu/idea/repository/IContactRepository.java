package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.entity.Contact;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Long> {}
