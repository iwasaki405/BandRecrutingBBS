package band.portfolio.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import band.portfolio.domain.band.model.Recruting;

@Mapper
public interface RecrutingMapper {

	/** 募集記事登録 */
	public void insertOne(Recruting recruting);
	
	/** 募集記事一覧取得 */
	public List<Recruting> findList();
}
