package band.portfolio.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import band.portfolio.domain.band.model.Recruiting;

@Mapper
public interface RecruitingMapper {

	/** 募集記事投稿 */
	public void insertRecruiting(Recruiting recruiting);

	/** 募集記事一覧取得 */
	public List<Recruiting> findRecruitingList(String searchParam);

	/** (MyPage)募集記事一覧取得 */
	public List<Recruiting> findRecruitingMyList(Integer userId);

	/** 募集記事1件取得 */
	public Recruiting findRecruitingById(Integer recruitingId);

	/** 募集記事修正  */
	public void modifyRecruiting(@Param("recruitingId") Integer recruitingId, @Param("title") String title,
			@Param("content") String content, @Param("area") String area, @Param("minAge") Integer minAge,
			@Param("maxAge") Integer maxAge, @Param("part") String part);

	/** 募集記事削除 */
	public void deleteRecruiting(Integer recruitingId);

}
