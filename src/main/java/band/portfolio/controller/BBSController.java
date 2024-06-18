package band.portfolio.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import band.portfolio.domain.band.model.Recruting;
import band.portfolio.domain.band.service.RecrutingService;
import band.portfolio.form.RecrutingForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BBSController {
	
	@Autowired
	private RecrutingService recrutingService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/top")
	public String getTopScreen(Model model) {
		
		/** 募集記事一覧を取得しModelに登録 */
		List<Recruting> recrutingList = recrutingService.getList();
		model.addAttribute("recrutingList", recrutingList);

		for(Recruting item : recrutingList) {
			item.setCreateDateTime(LocalDateTime.ofInstant(item.getCreateDateTime().toInstant(), ZoneId.systemDefault()));
		}
		
		return "top";
	}
	
	@GetMapping("/recruting")
	public String getRecruting(@ModelAttribute RecrutingForm form) {

		return "band/recruting";
	}
	
	/** 募集記事投稿処理 */
	@PostMapping("/recruting")
	public String postRecruting(@ModelAttribute RecrutingForm form) {
		
		log.info(form.toString());
		
		//formをRecrutingクラスに変換
		Recruting recruting = modelMapper.map(form, Recruting.class);
		
		//募集記事登録
		recrutingService.post(recruting);
		
		//Top画面にリダイレクト
		return "redirect:/top";
	}
	
	
}
