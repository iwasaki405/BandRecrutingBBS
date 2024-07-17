package band.portfolio.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import band.portfolio.domain.band.model.Recruiting;
import band.portfolio.domain.band.model.Reply;

@Mapper
public interface RecruitingMapper {

	/** 募集記事投稿 */
	public void insertOne(Recruiting recruiting);
	
	/** 募集記事一覧取得 */
	public List<Recruiting> findList(String searchParam);
	
	/** 募集記事1件取得 */
	public Recruiting findOne(Integer recruitingId);
	
	/** 募集記事削除 */
	public void deletePost(Integer recruitingId);
	
	/** 返信投稿 */
	public void postOne(Reply reply);
	
	/** 返信削除 */
	public void deleteReply(Integer replyId);
}
