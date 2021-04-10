package ru.urfu.idea.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.urfu.idea.entity.User;
import ru.urfu.idea.repository.IUserRepository;
import ru.urfu.idea.security.UserPrincipal;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<User> {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public Optional<User> getCurrentAuditor() {
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		User user = userRepository.findById(userPrincipal.getId())
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		return Optional.ofNullable(user);
	}
	
}
