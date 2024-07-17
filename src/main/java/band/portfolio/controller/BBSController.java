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

import band.portfolio.domain.band.model.Recruiting;
import band.portfolio.domain.band.model.Reply;
import band.portfolio.domain.band.service.RecruitingService;
import band.portfolio.form.IndividualPostForm;
import band.portfolio.form.RecruitingForm;
import band.portfolio.form.ReplyForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BBSController {
	
	@Autowired
	private RecruitingService recruitingService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/** TOP画面を表示 */
	@GetMapping("/top")
	public String getRecruitinggList(@RequestParam(name = "searchParam", required = false) String searchParam, Model model) {
		
		//掲示板のタイトルで検索した結果をModelに保存
		List<Recruiting> recruitingList = recruitingService.getList(searchParam);
		model.addAttribute("recruitingList", recruitingList);
		model.addAttribute("searchParam", searchParam);
		
		//TOP画面を表示
		return "top";
	}
	
	/** ログイン画面を表示 */
	@GetMapping("/login")
	public String getLogin() {
		
		//ログイン画面に遷移
		return "login/login";
	}
	
	/** 募集記事投稿画面を表示 */
	@GetMapping("/recruiting")
	public String getRecruiting(@ModelAttribute RecruitingForm form) {

		return "band/recruiting";
	}
	
	/** 募集記事投稿確認 */
	@PostMapping("/recruiting_confirm")
	public String postRecruitingConfirm(@ModelAttribute @Validated RecruitingForm form, BindingResult bindingResult) {
		
		log.info(form.toString());
		
		//入力チェック結果
		if(bindingResult.hasErrors()) {
			
			//NG:募集記事投稿画面に戻る
			return getRecruiting(form);
		}
		
		//募集記事投稿確認画面へ遷移
		return "band/recruiting_confirm";
	}
	
	/** 募集記事投稿処理 */
	@PostMapping("/recruiting")
	public String postRecruiting(@ModelAttribute RecruitingForm form) {
		
		log.info(form.toString());
		
		//formをRecruitingクラスに変換
		Recruiting recruiting = modelMapper.map(form, Recruiting.class);
		
		//募集記事登録
		recruitingService.post(recruiting);
		
		//Top画面にリダイレクト
		return "redirect:/top";
	}
	
	/** 募集記事個別画面表示 */
	@GetMapping("/individual_post/{recruitingId}")
	public String getPost(IndividualPostForm form, Model model, @PathVariable("recruitingId") Integer recruitingId) {
		
		//募集記事1件取得
		Recruiting recruiting = recruitingService.getPostOne(recruitingId);
		
		//RecruitingをFormに変換しModelに保存
		form = modelMapper.map(recruiting, IndividualPostForm.class);
		form.setReplyList(recruiting.getReplyList());
		model.addAttribute("individualPostForm", form);
		
		//募集記事個別画面へ遷移
		return "band/individual_post";
		
	}
	
	/** 募集記事削除 */
	@GetMapping("/delete/{recruitingId}")
	public String deletePost(@PathVariable("recruitingId") Integer recruitingId) {
		
		recruitingService.deletePostOne(recruitingId);
		
		return "redirect:/top";
		
	}
	
	/** 返信投稿 */
	@GetMapping("/reply/{recruitingId}")
	public String getReply(@ModelAttribute ReplyForm form) {
		
		log.info(form.toString());
		
		//返信投稿画面へ遷移
		return "band/reply";
	}
	
	/** 返信投稿確認 */
	@PostMapping("/reply_confirm")
	public String postReplyConfirm(@ModelAttribute @Validated ReplyForm form, BindingResult bindingResult) {
		
		log.info(form.toString());
		
		//入力チェック結果
		if(bindingResult.hasErrors()) {
			
			//NG:返信投稿画面に戻る
			return getReply(form);
		}
		
		//返信投稿確認画面へ遷移
		return "band/reply_confirm";
	}
	
	/** 返信投稿処理 */
	@PostMapping("/reply")
	public String postReply(@ModelAttribute ReplyForm form) {
		
		log.info(form.toString());
		
		//formをRecruitingクラスに変換
		Reply reply = modelMapper.map(form, Reply.class);
		
		//返信投稿
		recruitingService.reply(reply);
		
		//Top画面にリダイレクト
		return "redirect:/top";
	}
	
	/** 返信削除 */
	@GetMapping("/delete_reply/{replyId}")
	public String deleteReply(@PathVariable("replyId") Integer replyId) {
		
		recruitingService.deleteReplyOne(replyId);
		
		return "redirect:/top";
		
	}
}
