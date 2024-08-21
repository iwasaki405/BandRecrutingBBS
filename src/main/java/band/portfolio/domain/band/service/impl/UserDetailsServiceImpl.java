package band.portfolio.domain.band.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import band.portfolio.domain.band.model.UserWithName;
import band.portfolio.domain.band.model.Users;
import band.portfolio.domain.band.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		// ユーザー情報取得
		Users loginUser = userService.getLoginUser(userName);

		// ユーザーが存在しない場合
		if (loginUser == null) {
			throw new UsernameNotFoundException("user not found");
		}

		// 権限List作成
		GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole().toString());
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);

		// UserDetails生成
		UserDetails userDetails = (UserDetails) new UserWithName(loginUser.getUserId().toString(),
				loginUser.getPassword(), authorities, loginUser.getUserName());

		return userDetails;
	}
}