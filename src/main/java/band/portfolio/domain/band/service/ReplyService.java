package band.portfolio.domain.band.service;

import java.util.List;

import band.portfolio.domain.band.model.Reply;

public interface ReplyService {

	/** (MyPage)返信一覧取得 */
	public List<Reply> getReplyMyList(Integer userId);

	/** 返信投稿 */
	public void reply(Reply reply);

	/** 返信修正 */
	public void modifyReplyOne(Integer replyId, String content);

	/** 返信削除 */
	public void deleteReplyOne(Integer replyId);

}
