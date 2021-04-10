package ru.urfu.idea.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.urfu.idea.entity.User;
import ru.urfu.idea.mapper.IUserMapper;
import ru.urfu.idea.repository.IUserRepository;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final IUserRepository userRepository;
	private final IUserMapper userMapper;
	
	@Override
	public UserDetailsImpl loadUserByUsername(String s) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(s)
				.orElseThrow(() -> new UsernameNotFoundException("Problem during authentication!"));
		UserPrincipal userPrincipal = userMapper.modelToPrincipal(user);
		
		return new UserDetailsImpl(userPrincipal);
	}
	
}
