package band.portfolio.domain.band.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import band.portfolio.domain.band.model.Recruiting;
import band.portfolio.domain.band.service.RecruitingService;
import band.portfolio.repository.RecruitingMapper;

@Service
public class RecruitingServiceImpl implements RecruitingService {

	@Autowired
	private RecruitingMapper recruitingMapper;

	/** 募集記事投稿 */
	@Override
	public void RecruitingPost(Recruiting recruiting) {
		recruitingMapper.insertRecruiting(recruiting);
	}

	/** 募集記事一覧取得 */
	@Override
	public List<Recruiting> getRecruitingList(String searchParam) {
		return recruitingMapper.findRecruitingList(searchParam);
	}

	/** MyPage募集記事一覧取得 */
	@Override
	public List<Recruiting> getRecruitingMyList(Integer userId) {
		return recruitingMapper.findRecruitingMyList(userId);
	}

	/** 募集記事1件取得 */
	@Override
	public Recruiting getRecruitingById(Integer recruitingId) {
		return recruitingMapper.findRecruitingById(recruitingId);
	}

	/** 募集記事修正  */
	@Override
	public void modifyRecruitingOne(Integer recruitingId, String title, String content) {
		recruitingMapper.modifyRecruiting(recruitingId, title, content);
	}

	/** 募集記事削除 */
	@Override
	public void deleteRecruitingOne(Integer recruitingId) {
		recruitingMapper.deleteRecruiting(recruitingId);
	}
}
