package edu.kh.dgc.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.dgc.admin.model.service.AdminService;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.qna.model.dto.QnaComment;
import edu.kh.dgc.user.model.dto.User;
import oracle.jdbc.proxy.annotation.Post;

@Controller
public class AdminController {

	@Autowired
	private AdminService service;

	// 1.관리자 메인 대시보드
	@GetMapping("/admin")
	public String dashboard() {

		return "admin/admin_dashboard";
	}

	// 2.관리자 회원 관리
	@GetMapping("/adminUser") //
	public String adminUser(Model model) {

		List<User> adminUserList = service.adminUserList();

		model.addAttribute("adminUserList", adminUserList);

		
		return "admin/admin_user";
	}
	
	//2-1.관리자 회원 탈퇴 시키기
	@PostMapping(value = "/adminUser/deleteUserList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public int deleteUserList(@RequestBody User user) {
		
	   return service.userDelete(user.getUserNo());
	}
	

	// 3.관리자 영화 관리
	@GetMapping("/adminMovieManage") //
	public String movieManage(Model model) {

		List<Movie> adminMovieList = service.adminMovieList();

		model.addAttribute("adminMovieList", adminMovieList);

		System.out.println(adminMovieList);
		
		return "admin/admin_movieManage";
	}

	// 3-1.관리자 영화 등록
	@GetMapping("/adminMovieRegister") //
	public String movieRegister() {

		return "admin/admin_movieManageDetail";
	}

	// 4.관리자 상영 관리
	@GetMapping("/adminCinemaManage") //
	public String cinemaManage() {

		return "admin/admin_cinemaManage";
	}

	// 4-1.관리자 상영 시간 등록
	@GetMapping("/adminCinemaRegister") //
	public String cinemaRegister() {

		return "admin/admin_cinemaMangeDetail";
	}

	// 5.관리자 공지사항 리스트 조회
	@GetMapping("/adminNotice") //
	public String notice(Model model) {
		
		List<Notice> adminNoticeList = service.adminNoticeList();

		model.addAttribute("adminNoticeList", adminNoticeList);
		
		
		return "admin/admin_notice";
	}

	// 5-1.공지사항 게시글 조회
	@GetMapping("/adminNoticeRead/{noticeNo}") //
	public String noticeRead(Model model, @PathVariable(value = "noticeNo", required = false) int noticeNo,Notice notice) {

		
		List<Notice> adminNoticeOne = service.adminNoticeOne(notice);

		model.addAttribute("adminNoticeOne", adminNoticeOne);
		
		
		return "admin/admin_notice_read";
	}
	
	//5-1.공지사항 게시글 검색
	@GetMapping("/searchNotice")
	public String searchNotice(@RequestParam("keyword") String keyword, @RequestParam("option") String option, Model model) {
	    List<Notice> searchResults;

	    if (option.equals("title")) {
	        // 번호 열에서 키워드 검색 쿼리를 실행
	        searchResults = service.searchNotice(keyword);
	    } else {
	        // 다른 옵션에 대한 검색 로직 추가
	        // ...
	        // 예를 들어, 이름 열 또는 이메일 열에 대한 검색 쿼리를 실행할 수 있습니다.
	        searchResults = service.searchByTitleOrEmail(keyword, option);
	    }

	    model.addAttribute("searchResults", searchResults);
	    return "searchResults"; // 검색 결과를 표시할 뷰의 이름을 반환합니다.
	}


	

	// 5-2. 공지사항 게시글 쓰기
	@GetMapping("/adminNoticeWrite") 
	public String noticeWrite() {

		return "admin/admin_notice_write";
	}
	
	// 5-2. 공지사항 게시글 쓰기 - 삽입------------------------------------------------아직 안 함------
	@PostMapping("/adminNoticeWriteInsert") 
	public String noticeWriteInsert(Model model,Notice notice,RedirectAttributes ra) {

		int noticeList = service.noticeWriteInsert(notice);
		
		if(noticeList > 0) {
			ra.addFlashAttribute("message","성공");
		}else {
			ra.addFlashAttribute("message","실패");
		}
		
		
		model.addAttribute("noticeList",noticeList);
		
		return "redirect:/admin/adminNotice";
	}

	
	// 5-3. 공지사항 게시글 수정
	@GetMapping("/adminNoticeUpate") 
	public String noticeUpdate() {

		return "admin/admin_notice_update";
	}

	// 5-4. 공지사항 게시글 삭제 --- 페이지 없음
	@GetMapping("/adminNoticeRead/{noticeNo}/delete") //
	public String noticeDelete(@PathVariable("noticeNo") int noticeNo, RedirectAttributes ra) {
		int noticeNoCheck = service.noticeDelete(noticeNo);

		// 삽입 성공 시
		String message = null;
		String path = "redirect:/adminNotice";
		if (noticeNoCheck > 0) { // 성공시
			message = "게시글이 삭제되었습니다.";
		} else {
			message = "게시글 삭제 실패";
		}

		ra.addFlashAttribute("message", message);
		return path;	
		
	}
	
	//5-5 공지사항 게시글 선택 삭제
	
		@PostMapping(value = "/adminNotice/deleteQnaList", produces = "application/json; charset=UTF-8")
		@ResponseBody
		public int deleteNoticeList(@RequestBody Notice notice) {
			
		   return service.noticeDelete(notice.getNoticeNo());
		}
	
	


	// 6. 1:1 문의사항 리스트 조회 230613
	@GetMapping("/adminQna") //
	public String qnaList(Model model) {

		List<Qna> adminQnaList = service.adminQnaList();

		model.addAttribute("adminQnaList", adminQnaList);

		return "admin/admin_QNA";
	}

	// 6-1. 1:1 문의사항 게시글 조회 230613
	@GetMapping("/adminQnaRead/{qnaNo}") //
	public String qnaRead(Model model, @PathVariable(value = "qnaNo", required = false) int qnaNo,QnaComment qnaComment) {
		

			Qna qna = service.selectQnaOne(qnaNo);
			QnaComment qnaCommentList = service.selectQnaCommentList(qnaComment);

			qna.setQnaNo(qnaNo);
			model.addAttribute("Qna", qna);
			model.addAttribute("QnaComment", qnaCommentList);
	

		return "admin/admin_QNA_read";
	}

	// 6-2. 1:1 문의사항 게시글 쓰기 230613
	@GetMapping("/adminQnaWrite") //
	public String qnaWrite() {

		return "admin/admin_QNA_write";
	}

	
	  //6-2. 1:1 문의사항 게시글 쓰기 - 삽입 230614
	  
	  @GetMapping("/adminQnaWriteInsert")
	  public String qnaWriteIinsert(Qna qna,Model model) {

		  int qnaNo =  service.qnaInsert(qna);
		  
		  model.addAttribute("Qna",qna);
	  
		  return "admin/admin_QNA_write"; 
	  }
	  //6-2-1. 1:1 문의사항 답변 게시글 쓰기 - 삽입 230615
	  
	  @PostMapping("/adminQnaAnswer/{qnaNo}")
	  public String qnaAnswerInsert(Qna qna,Model model, @PathVariable(value = "qnaNo", required = false) int qnaNo, QnaComment qnaComment,RedirectAttributes ra) {
		
		  
		  if((qnaComment.getQnaComment()) != null) {
			  
			  int qnaUpdateResult = service.qnaAnswerUpdate(qnaComment);		  
			  

			  if(qnaUpdateResult > 0) {
				  ra.addFlashAttribute("message","성공");
				
			  }else {
				  ra.addFlashAttribute("message","실패");
			  }
		  }else {
			
			  qnaComment.setQnaNo(qnaNo);
				qnaComment.setQnaComment(qnaComment.getQnaComment());
				
				  int qnaResult = service.qnaAnswerInsert(qnaComment);
				  
				  QnaComment qnaFlUpdate = service.updateAnswer(qnaNo);

				  if(qnaResult > 0) {
					  ra.addFlashAttribute("message","성공");
					
				  }else {
					  ra.addFlashAttribute("message","실패");
				  }
		  }
		  return "redirect:" + qnaNo; 
	  }

	// 6-2. 1:1 문의사항 게시글 수정화면 전환 230614
	@GetMapping("/adminQnaUpdate/{qnaNo}")
	public String qnaUpdate(Model model, @PathVariable(value = "qnaNo", required = false) int qnaNo) {

		Qna qna = service.selectQnaOne(qnaNo);

		qna.setQnaNo(qnaNo);
		model.addAttribute("Qna", qna);
		System.out.println(qna);

		return "admin/admin_QNA_update";
	}

	// 6-2-1. 1:1 문의사항 게시글 수정 230614

	@PostMapping("/adminQnaUpdate/{qnaNo}")
	public String qnaUpdate(Qna qna, Model model, @PathVariable(value = "qnaNo") int qnaNo, RedirectAttributes ra) {

		int qnaNoCheck = service.qnaUpdate(qna);

		Qna qnaList = new Qna();

		model.addAttribute("Qna", qnaList);

		// 삽입 성공 시
		String message = null;
		String path = "redirect:";
		if (qnaNoCheck > 0) { // 성공시

			message = "게시글이 등록 되었습니다.";
			path += "/adminQnaRead" + "/" + qnaNo;

		} else {
			message = "게시글이 등록 실패 되었습니다.";
			path += "adminQnaupdate";
		}

		ra.addFlashAttribute("message", message);
		return path;

	}

	// 6-3. 1:1 문의사항 게시글 삭제
	@GetMapping("/adminQnaRead/{qnaNo}/delete")
	public String qnaDelete(@PathVariable("qnaNo") int qnaNo, RedirectAttributes ra) {
		int qnaNoCheck = service.qnaDelete(qnaNo);

		// 삽입 성공 시
		String message = null;
		String path = "redirect:/adminQna";
		if (qnaNoCheck > 0) { // 성공시
			message = "게시글이 삭제되었습니다.";
		} else {
			message = "게시글 삭제 실패";
		}

		ra.addFlashAttribute("message", message);
		return path;
	}
	
	//6-4 1:1 문의 게시글 선택 삭제
	
	@PostMapping(value = "/adminQna/deleteQnaList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public int deleteQnaList(@RequestBody Qna qna) {
		
	   return service.qnaDelete(qna.getQnaNo());
	}

	// 7. FAQ 리스트 조회
	@GetMapping("/adminFaq") //
	public String faqList() {

		return "admin/admin_FAQ";
	}

	// 7-1. FAQ 게시글 조회
	@GetMapping("/adminFaqRead") //
	public String faqRead() {

		return "admin/admin_FAQ_read";
	}

	// 7-2. FAQ 게시글 쓰기
	@GetMapping("/adminFawWrite") //
	public String faqWrite() {

		return "admin/admin_FAQ_Write";
	}

	// 7-2. FAQ게시글 수정
	@GetMapping("/adminFaqUpdate") //
	public String faqUpdate() {

		return "admin/admin_FAQ_update";
	}

	// 7-3. FAQ 게시글 삭제
	@GetMapping("/adminFaqDelete") //
	public String faqDelete() {

		return "admin/admin_FAQ_Delete";
	}

	// 8. 신고하기 리스트 조회
	@GetMapping("/adminReport") //
	public String report() {

		return "admin/admin_report";
	}

	@GetMapping("/adminMain") //
	public String main() {

		return "admin/admin_main";
	}

	// 8-1. 신고하기 게시글 조회

	// 8-2. 신고하기 게시글 쓰기

	// 8-2. 신고하기 게시글 수정

	// 8-3. 신고하기 게시글 삭제

}
