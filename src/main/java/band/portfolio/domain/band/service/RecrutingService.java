package band.portfolio.domain.band.service;

import java.util.List;

import band.portfolio.domain.band.model.Recruting;

public interface RecrutingService {

	/** 募集記事登録 */
	public void post(Recruting recruting);
	
	/** 募集記事一覧取得 */
	public List<Recruting> getList();
}
