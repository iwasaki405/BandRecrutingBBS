package band.portfolio.domain.band.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import band.portfolio.domain.band.model.Recruiting;
import band.portfolio.domain.band.model.Reply;
import band.portfolio.domain.band.service.RecruitingService;
import band.portfolio.repository.RecruitingMapper;

@Service
public class RecruitingServiceImpl implements RecruitingService {
	
	@Autowired
	private RecruitingMapper mapper;

	/** 募集記事投稿 */
	@Override
	public void post(Recruiting recruiting) {
		mapper.insertOne(recruiting);
	}
	
	/** 募集記事一覧取得 */
	@Override
	public List<Recruiting> getList(String searchParam){
		return mapper.findList(searchParam);
	}
	
	/** 募集記事1件取得 */
	@Override
	public Recruiting getPostOne(Integer recruitingId) {
		return mapper.findOne(recruitingId);
	}
	
	/** 募集記事削除 */
	@Override
	public void deletePostOne(Integer recruitingId) {
		mapper.deletePost(recruitingId);
	}
	
	/** 返信投稿 */
	@Override
	public void reply(Reply reply) {
		mapper.postOne(reply);
	}
	
	/** 返信削除 */
	@Override
	public void deleteReplyOne(Integer replyId) {
		mapper.deleteReply(replyId);
	}
}
