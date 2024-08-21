package band.portfolio.domain.band.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import band.portfolio.domain.band.model.Reply;
import band.portfolio.domain.band.service.ReplyService;
import band.portfolio.repository.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper mapper;

	/** (MyPage)返信一覧取得 */
	public List<Reply> getReplyMyList(Integer userId) {
		return mapper.findReplyMyList(userId);
	}

	/** 返信投稿 */
	@Override
	public void reply(Reply reply) {
		mapper.insertReply(reply);
	}

	/** 返信修正 */
	@Override
	public void modifyReplyOne(Integer replyId, String content) {
		mapper.modifyReply(replyId, content);
	}

	/** 返信削除 */
	@Override
	public void deleteReplyOne(Integer replyId) {
		mapper.deleteReply(replyId);
	}

}
