package band.portfolio.domain.band.service.impl;

import java.util.Arrays;
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

	/** 都道府県のArrayを生成する */
	public String[] getAreaArray() {
		String[] areaArray = { "北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県", "茨城県", "栃木県",
				"群馬県", "埼玉県", "千葉県", "東京都", "神奈川県", "新潟県", "富山県", "石川県", "福井県", "山梨県",
				"長野県", "岐阜県", "静岡県", "愛知県", "三重県", "滋賀県", "京都府", "大阪府", "兵庫県", "奈良県",
				"和歌山県", "鳥取県", "島根県", "岡山県", "広島県", "山口県", "徳島県", "香川県", "愛媛県", "高知県",
				"福岡県", "佐賀県", "長崎県", "熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県", "オンライン"
		};
		//List<String> areaList = Arrays.asList(area);
		return areaArray;
	}

	/** 募集パートのListを生成する */
	@Override
	public List<String> getPartList() {
		String[] partArray = { "Vo", "Gt", "Ba", "Dr", "Key", "その他" };
		List<String> partList = Arrays.asList(partArray);
		return partList;
	}

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
	public void modifyRecruitingOne(Integer recruitingId, String title, String content, String area, Integer minAge,
			Integer maxAge, String part) {
		recruitingMapper.modifyRecruiting(recruitingId, title, content, area, minAge, maxAge, part);
	}

	/** 募集記事削除 */
	@Override
	public void deleteRecruitingOne(Integer recruitingId) {
		recruitingMapper.deleteRecruiting(recruitingId);
	}
}
