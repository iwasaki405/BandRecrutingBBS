package band.portfolio.domain.band.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import band.portfolio.domain.band.model.Users;
import band.portfolio.domain.band.service.UserService;
import band.portfolio.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper mapper;

	/** ログインユーザー情報取得 */
	@Override
	public Users getLoginUser(String userName) {
		
		return mapper.findLoginUser(userName);
		
	}
	
}
