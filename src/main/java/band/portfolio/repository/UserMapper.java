package band.portfolio.repository;

import org.apache.ibatis.annotations.Mapper;

import band.portfolio.domain.band.model.Users;

@Mapper
public interface UserMapper {

	/** ログインユーザー情報取得 */
	public Users findLoginUser(String userName);
	
}
