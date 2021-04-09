package ru.urfu.idea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.urfu.idea.model.Algorithm;
import ru.urfu.idea.model.User;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private SCryptPasswordEncoder sCryptPasswordEncoder;
	
	@Transactional
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		
		User user = userService.loadUserByUsername(username);
		Algorithm algorithm = user.getAlgorithm();
		
		switch (algorithm.getName()) {
			case BCRYPT:
				return checkPassword(user, password, bCryptPasswordEncoder);
			case SCRYPT:
				return checkPassword(user, password, sCryptPasswordEncoder);
		}
		
		throw new BadCredentialsException("Bad credentials");
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
	}
	
	private  Authentication checkPassword(User user, String rawPassword, PasswordEncoder encoder) {
		if (encoder.matches(rawPassword, user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(
					user.getUsername(),
					user.getPassword(),
					user.getAuthorities());
		} else {
			throw new BadCredentialsException("Bad credentials");
		}
	}
	
}
