package band.portfolio.domain.band.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserWithName extends User {

	private final String userName;

	public UserWithName(String userId, String password,
			Collection<? extends GrantedAuthority> authorities, String userName) {
		super(userId, password, authorities);
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

}
