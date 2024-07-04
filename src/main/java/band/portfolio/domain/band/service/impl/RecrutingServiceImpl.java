package band.portfolio.domain.band.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import band.portfolio.domain.band.model.Recruting;
import band.portfolio.domain.band.service.RecrutingService;
import band.portfolio.repository.RecrutingMapper;

@Service
public class RecrutingServiceImpl implements RecrutingService {
	
	@Autowired
	private RecrutingMapper mapper;

	/** 募集記事登録 */
	@Override
	public void post(Recruting recruting) {
		mapper.insertOne(recruting);
	}
	
	/** 募集記事一覧取得 */
	@Override
	public List<Recruting> getList(String searchParam){
		return mapper.findList(searchParam);
	}
	
	/** 募集記事1件取得 */
	@Override
	public Recruting getPostOne(Integer recrutingId) {
		return mapper.findOne(recrutingId);
	}
}
