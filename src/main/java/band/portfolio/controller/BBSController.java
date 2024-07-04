package band.portfolio.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import band.portfolio.domain.band.model.Recruting;
import band.portfolio.domain.band.service.RecrutingService;
import band.portfolio.form.IndividualPostForm;
import band.portfolio.form.RecrutingForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BBSController {
	
	@Autowired
	private RecrutingService recrutingService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/** TOP画面を表示 */
	@GetMapping("/top")
	public String getRecrutingList(@RequestParam(name = "searchParam", required = false) String searchParam, Model model) {
		
		//掲示板のタイトルで検索した結果をModelに保存
		List<Recruting> recrutingList = recrutingService.getList(searchParam);
		model.addAttribute("recrutingList", recrutingList);
		model.addAttribute("searchParam", searchParam);
		
		//TOP画面を表示
		return "top";
	}
	
	/** 募集記事投稿画面を表示 */
	@GetMapping("/recruting")
	public String getRecruting(@ModelAttribute RecrutingForm form) {

		return "band/recruting";
	}
	
	/** 募集記事投稿確認 */
	@PostMapping("/recruting_confirm")
	public String postRecrutingConfirm(@ModelAttribute @Validated RecrutingForm form, BindingResult bindingResult) {
		
		log.info(form.toString());
		
		//入力チェック結果
		if(bindingResult.hasErrors()) {
			
			//NG:募集記事投稿画面に戻る
			return getRecruting(form);
		}
		
		//募集記事投稿確認画面に遷移
		return "band/recruting_confirm";
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
	
	/** 募集記事個別画面表示 */
	@GetMapping("/individual_post/{recrutingId}")
	public String getPost(IndividualPostForm form, Model model, @PathVariable("recrutingId") Integer recrutingId) {
		
		//募集記事1件取得
		Recruting recruting = recrutingService.getPostOne(recrutingId);
		
		//RecrutingをFormに変換しModelに保存
		form = modelMapper.map(recruting, IndividualPostForm.class);
		model.addAttribute("individualPostForm", form);
		
		//募集記事個別画面を表示
		return "band/individual_post";
		
	}
	
}
