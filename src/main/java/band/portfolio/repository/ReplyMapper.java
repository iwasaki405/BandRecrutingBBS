package band.portfolio.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import band.portfolio.domain.band.model.Reply;

@Mapper
public interface ReplyMapper {

	/** (MyPage)返信一覧取得 */
	public List<Reply> findReplyMyList(Integer userId);

	/** 返信投稿 */
	public void insertReply(Reply reply);

	/** 返信修正 */
	public void modifyReply(@Param("replyId") Integer replyId, @Param("content") String content);

	/** 返信削除 */
	public void deleteReply(Integer replyId);

}
