package band.portfolio.domain.band.service;

import java.util.List;

import band.portfolio.domain.band.model.Recruiting;
import band.portfolio.domain.band.model.Reply;

public interface RecruitingService {

	/** 募集記事投稿 */
	public void post(Recruiting recruiting);
	
	/** 募集記事一覧取得 */
	public List<Recruiting> getList(String searchParam);
	
	/** 募集記事1件取得 */
	public Recruiting getPostOne(Integer recruitingId);
	
	/** 募集記事削除 */
	public void deletePostOne(Integer recruitingId);
	
	/** 返信投稿 */
	public void reply(Reply reply);
	
	/** 返信削除 */
	public void deleteReplyOne(Integer replyId);
}
