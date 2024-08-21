package band.portfolio.domain.band.service;

import java.util.List;

import band.portfolio.domain.band.model.Recruiting;

public interface RecruitingService {

	/** 募集記事投稿 */
	public void RecruitingPost(Recruiting recruiting);

	/** 募集記事一覧取得 */
	public List<Recruiting> getRecruitingList(String searchParam);

	/** (MyPage)募集記事一覧取得 */
	public List<Recruiting> getRecruitingMyList(Integer userId);

	/** 募集記事1件取得 */
	public Recruiting getRecruitingById(Integer recruitingId);

	/** 募集記事削除 */
	public void deleteRecruitingOne(Integer recruitingId);

}
