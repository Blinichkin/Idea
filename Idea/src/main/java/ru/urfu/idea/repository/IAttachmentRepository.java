package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.entity.Attachment;

@Repository
public interface IAttachmentRepository extends JpaRepository<Attachment, Long> {}
