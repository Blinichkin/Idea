package ru.urfu.idea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.urfu.idea.model.Role;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
	
	List<Role> findByName(String name);
	
}
