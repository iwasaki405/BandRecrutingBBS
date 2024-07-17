package band.portfolio.domain.band.service;

import band.portfolio.domain.band.model.Users;

public interface UserService {

	/** ログインユーザー情報取得 */
	public Users getLoginUser(String userName);
	
}
