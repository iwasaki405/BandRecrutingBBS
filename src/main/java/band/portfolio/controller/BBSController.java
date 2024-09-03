package band.portfolio.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import band.portfolio.domain.band.model.Recruiting;
import band.portfolio.domain.band.model.Reply;
import band.portfolio.domain.band.service.RecruitingService;
import band.portfolio.domain.band.service.ReplyService;
import band.portfolio.form.IndividualPostForm;
import band.portfolio.form.RecruitingForm;
import band.portfolio.form.ReplyForm;
import band.portfolio.form.ReplyModifyForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BBSController {

	@Autowired
	private RecruitingService recruitingService;

	@Autowired
	private ReplyService replyService;

	@Autowired
	private ModelMapper modelMapper;

	/** TOP画面を表示 */
	@GetMapping("/top")
	public String getTop(@RequestParam(name = "searchParam", required = false) String searchParam, Model model) {

		//部分一致検索した結果をModelに保存
		List<Recruiting> recruitingList = recruitingService.getRecruitingList(searchParam);
		model.addAttribute("recruitingList", recruitingList);
		model.addAttribute("searchParam", searchParam);

		//TOP画面へ遷移
		return "top";
	}

	/** ログイン画面表示 */
	@GetMapping("/login")
	public String getLogin() {

		//ログイン画面へ遷移
		return "login/login";
	}

	/** マイページ画面表示 */
	@GetMapping("/mypage")
	public String getMypage(Model model) {

		// SecurityContextHolderからAuthenticationオブジェクトを取得
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		// AuthenticationオブジェクトからuserIdを取得し各Listにset
		List<Recruiting> recruitingList = recruitingService
				.getRecruitingMyList(Integer.parseInt(authentication.getName()));
		model.addAttribute("recruitingList", recruitingList);
		List<Reply> replyList = replyService.getReplyMyList(Integer.parseInt(authentication.getName()));
		model.addAttribute("replyList", replyList);

		//マイページ画面へ遷移
		return "band/mypage";
	}

	/** 募集記事投稿画面を表示 */
	@GetMapping("/recruiting")
	public String getRecruiting(@ModelAttribute RecruitingForm recruitingForm) {

		//募集記事投稿画面へ遷移
		return "band/recruiting";
	}

	/** 募集記事投稿処理 */
	@PostMapping("/recruiting")
	public String postRecruiting(@ModelAttribute @Validated RecruitingForm recruitingForm, BindingResult bindingResult,
			Model model) {

		// SecurityContextHolderからAuthenticationオブジェクトを取得
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		// AuthenticationオブジェクトからuserIdを取得しformにset
		recruitingForm.setUserId(Integer.parseInt(authentication.getName()));

		log.info(recruitingForm.toString());

		//入力チェック結果
		if (bindingResult.hasErrors()) {

			model.addAttribute("recruitingForm", recruitingForm);

			//NG:募集記事投稿画面に戻る
			return "band/recruiting";
		}

		//formをRecruitingクラスに変換
		Recruiting recruiting = modelMapper.map(recruitingForm, Recruiting.class);

		//募集記事投稿
		recruitingService.RecruitingPost(recruiting);

		//Top画面にリダイレクト
		return "redirect:/top";
	}

	/** 募集記事個別画面表示 */
	@GetMapping("/individual_post/{recruitingId}")
	public String getIndividualPost(IndividualPostForm individualPostForm,
			@ModelAttribute RecruitingForm recruitingForm,
			@ModelAttribute ReplyForm replyForm,
			@ModelAttribute ReplyModifyForm replyModifyForm, Model model,
			@PathVariable("recruitingId") Integer recruitingId) {

		//募集記事を1件取得しmodelに保存
		populateIndividualPostFormModel(individualPostForm, model, individualPostForm.getRecruitingId());

		//募集記事個別画面へ遷移
		return "band/individual_post";

	}

	/** 募集記事修正  */
	@PostMapping("/modifyRecruiting/{recruitingId}")
	public String modifyRecruiting(IndividualPostForm individualPostForm,
			@ModelAttribute @Validated RecruitingForm recruitingForm, BindingResult bindingResult,
			@ModelAttribute ReplyForm replyForm, @ModelAttribute ReplyModifyForm replyModifyForm, Model model,
			@PathVariable("recruitingId") Integer recruitingId,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("recruitingForm", recruitingForm);

			//募集記事を1件取得しmodelに保存
			populateIndividualPostFormModel(individualPostForm, model, individualPostForm.getRecruitingId());

			return "band/individual_post";
		}

		//募集記事修正
		recruitingService.modifyRecruitingOne(recruitingId, recruitingForm.getTitle(), recruitingForm.getContent());

		//募集記事を1件取得しmodelに保存
		populateIndividualPostFormModel(individualPostForm, model, individualPostForm.getRecruitingId());

		//元いたバンド募集記事個別画面にに戻る
		return "redirect:/individual_post/" + recruitingId;

	}

	/** 募集記事削除 */
	@GetMapping("/delete/{recruitingId}")
	public String deletePost(@PathVariable("recruitingId") Integer recruitingId) {

		//募集記事を削除
		recruitingService.deleteRecruitingOne(recruitingId);

		//MyPageにリダイレクト
		return "redirect:/mypage";
	}

	/** 返信投稿 */
	@PostMapping("/reply/{recruitingId}")
	public String postReply(IndividualPostForm individualPostForm, @ModelAttribute @Validated ReplyForm replyForm,
			BindingResult bindingResult, @ModelAttribute RecruitingForm recruitingForm,
			@ModelAttribute ReplyModifyForm replyModifyForm, Model model, RedirectAttributes redirectAttributes) {

		// SecurityContextHolderからAuthenticationオブジェクトを取得
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		// AuthenticationオブジェクトからuserIdを取得しformにset
		replyForm.setUserId(Integer.parseInt(authentication.getName()));

		log.info(replyForm.toString());

		if (bindingResult.hasErrors()) {

			model.addAttribute("replyForm", replyForm);

			// 募集記事1件取得
			populateIndividualPostFormModel(individualPostForm, model, individualPostForm.getRecruitingId());

			//元のページに戻る
			return "band/individual_post";

		}

		//replyFormをReplyクラスに変換
		Reply reply = modelMapper.map(replyForm, Reply.class);

		//返信投稿
		replyService.reply(reply);

		// 元いたバンド募集記事個別画面にリダイレクト
		redirectAttributes.addFlashAttribute("recruitingId", individualPostForm.getRecruitingId());
		return "redirect:/individual_post/{recruitingId}";
	}

	/** 返信修正 */
	@PostMapping("/modify/{replyId}")
	public String modifyReply(IndividualPostForm individualPostForm,
			@ModelAttribute @Validated ReplyModifyForm replyModifyForm, BindingResult bindingResult,
			@ModelAttribute RecruitingForm recruitingForm,
			@ModelAttribute ReplyForm replyForm, Model model, @PathVariable("replyId") Integer replyId,
			@RequestParam("recruitingId") Integer recruitingId, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("replymModifyForm", replyModifyForm);

			//募集記事を1件取得しmodelに保存
			populateIndividualPostFormModel(individualPostForm, model, individualPostForm.getRecruitingId());

			return "band/individual_post";

		}

		//返信修正
		replyService.modifyReplyOne(replyId, replyModifyForm.getContent());

		//募集記事を1件取得しmodelに保存
		populateIndividualPostFormModel(individualPostForm, model, individualPostForm.getRecruitingId());

		//元いたバンド募集記事個別画面にに戻る
		return "redirect:/individual_post/" + recruitingId;
	}

	/** 返信削除 */
	@PostMapping("/delete_reply/{replyId}")
	public String deleteReply(@PathVariable("replyId") Integer replyId,
			@RequestParam("recruitingId") Integer recruitingId, RedirectAttributes redirectAttributes) {

		// 返信削除
		replyService.deleteReplyOne(replyId);

		// 元いたバンド募集記事個別画面にリダイレクト
		return "redirect:/individual_post/" + recruitingId;
	}

	/** 募集記事を1件取得しmodelに保存 */
	private void populateIndividualPostFormModel(IndividualPostForm individualForm, Model model, Integer recruitingId) {

		//募集記事1件取得
		Recruiting recruiting = recruitingService.getRecruitingById(recruitingId);

		//RecruitingをIndividualPostFormに変換しmodelに保存
		individualForm = modelMapper.map(recruiting, IndividualPostForm.class);
		individualForm.setReplyList(recruiting.getReplyList());
		model.addAttribute("individualPostForm", individualForm);

	}
}
