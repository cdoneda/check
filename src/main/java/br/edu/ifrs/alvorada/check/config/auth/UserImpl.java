package br.edu.ifrs.alvorada.check.config.auth;


import br.edu.ifrs.alvorada.check.domain.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserImpl extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = -6447788540288283745L;
	private User user;

	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities, User user) {
		super(username, password, authorities);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
