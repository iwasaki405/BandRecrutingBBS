package band.portfolio.domain.band.service;

import java.util.List;

import band.portfolio.domain.band.model.Recruiting;

public interface RecruitingService {

	/** 都道府県のArrayを生成する */
	public String[] getAreaArray();

	/** 募集パートのListを生成する */
	public List<String> getPartList();

	/** 募集記事投稿 */
	public void RecruitingPost(Recruiting recruiting);

	/** 募集記事一覧取得 */
	public List<Recruiting> getRecruitingList(String searchParam);

	/** (MyPage)募集記事一覧取得 */
	public List<Recruiting> getRecruitingMyList(Integer userId);

	/** 募集記事1件取得 */
	public Recruiting getRecruitingById(Integer recruitingId);

	/** 募集記事修正  */
	public void modifyRecruitingOne(Integer recruitingId, String title, String content, String area, Integer minAge,
			Integer maxAge, String part);

	/** 募集記事削除 */
	public void deleteRecruitingOne(Integer recruitingId);

}
