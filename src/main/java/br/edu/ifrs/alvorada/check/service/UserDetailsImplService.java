package br.edu.ifrs.alvorada.check.service;


import br.edu.ifrs.alvorada.check.config.auth.UserImpl;
import br.edu.ifrs.alvorada.check.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsImplService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails 	loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!StringUtils.isEmpty(username))
			return this.userRepository.findByUsername(username)
					.map(user -> new UserImpl(user.getUsername(), user.getPassword(),
							user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole()))
									.collect(Collectors.toList()),
							user))
					.orElseThrow(() -> new UsernameNotFoundException("couldn't find " + username + "!"));
		else
			throw new UsernameNotFoundException("couldn't find empty username!");
	}
}