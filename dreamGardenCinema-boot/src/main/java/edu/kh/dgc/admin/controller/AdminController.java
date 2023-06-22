package edu.kh.dgc.admin.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.dgc.admin.model.dto.Admin;
import edu.kh.dgc.admin.model.service.AdminService;
import edu.kh.dgc.customerservice.model.dto.FAQ;
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
	
	/*
	 * //1-1. 관리자 로그인 화면
	 * 
	 * public String showAdminPage(Model model) {
	 * 
	 * List<User> adminUser = service.getAdminDetails();
	 * 
	 * if (!adminUser.isEmpty()) { User admin = adminUser.get(0); // 첫 번째 관리자 사용자
	 * 객체를 가져옴 model.addAttribute("admin", admin); System.out.println(admin); }
	 * 
	 * return "admin/admin_sideBar";}
	 */

	
	
	// 2.관리자 회원 관리
	@GetMapping("/adminUser") //
	public String adminUser(Model model) {

		List<User> adminUserList = service.adminUserList();

		model.addAttribute("adminUserList", adminUserList);

		return "admin/admin_user";
	}

	// 2-1.관리자 회원 탈퇴 시키기
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
	@GetMapping("/adminMovieRegister") 
	public String movieRegister() {

		return "admin/admin_movieManageDetail";
	}

	// 4.관리자 상영 관리
	@GetMapping("/adminCinemaManage") 
	public String cinemaManage(Model model) {
		
		List<Movie> cinemaList = service.adminCinemaList();
		
		model.addAttribute("cinemaList", cinemaList);

		return "admin/admin_cinemaManage";
	}

	//2관으로 넘어가기
	@RequestMapping("/cinema/{movieTheater}") 
	public String cinema(Model model, @PathVariable(value="movieTheater", required=false) String movieTheaterNo) {
		
		List<Movie> cinemaList = service.adminCinemaTwo(movieTheaterNo);
		
		model.addAttribute("cinemaList", cinemaList);

		System.out.println(movieTheaterNo);
		System.out.println(cinemaList);
		
		return "admin/admin_cinemaManage" +"/"+ movieTheaterNo;
	}	

	// 4-1.관리자 상영 시간 등록
	@GetMapping("/adminCinemaRegister") 
	public String cinemaRegister(Model model, Movie movie) {

		List<Movie> cinemaList = service.cinemaList();
		
		model.addAttribute("cinemaList",cinemaList);
		
		return "admin/admin_cinemaMangeDetail";
	}
	// 4-2.관리자 상영 시간 등록 (insert)
	@GetMapping("/adminCinemaRegisterinsert") 
	public String cinemaRegisterinsert(Model model,Movie movie) {

		int result = service.cinemaListInsert(movie);
		
		
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
	@GetMapping("/adminNoticeRead/{noticeNo}") 
	public String noticeRead(Model model, @PathVariable(value = "noticeNo", required = false) int noticeNo,
			Notice notice) {

		List<Notice> adminNoticeOne = service.adminNoticeOne(notice);

		model.addAttribute("adminNoticeOne", adminNoticeOne);

		return "admin/admin_notice_read";
	}

	// 5-1.공지사항 게시글 검색
	@GetMapping("/getNoticeSearchList")
	public String getNoticeSearchList(@Param("type") String type, @Param("keyword") String keyword, Model model) {

		Notice noticeList = new Notice();
		noticeList.setType(type);
		noticeList.setKeyword(keyword);

		List<Notice> adminNoticeList = service.getNoticeSearchList(noticeList);
		model.addAttribute("adminNoticeList", adminNoticeList); 
		
		System.out.println(noticeList);
		System.out.println(adminNoticeList);

		return "admin/admin_notice";

	}
	

	// 5-2. 공지사항 게시글 쓰기
	@GetMapping("/adminNoticeWrite")
	public String noticeWrite() {

		return "admin/admin_notice_write";
	}

	// 5-2. 공지사항 게시글 쓰기 - 삽입
	
	@PostMapping("/adminNoticeWriteInsert")
	public String noticeWriteInsert(Notice notice, Model model) {
		
		int result = service.noticeWriteInsert(notice);
		
		int noticeNo = notice.getNoticeNo();
		
		System.out.println(noticeNo);

		model.addAttribute("notice", notice);

		return "redirect:/adminNoticeRead" +"/" + noticeNo;
}

	

	// 5-3. 공지사항 게시글 수정 화면 전환
	@GetMapping("/adminNoticeUpdate/{noticeNo}")
	public String noticeUpdate(Model model, @PathVariable(value = "noticeNo", required = false) int noticeNo,Notice notice) {
		
		List<Notice> adminNoticeList = service.adminNoticeOne(notice);
		notice.setNoticeNo(noticeNo);

		model.addAttribute("adminNoticeList", adminNoticeList);
		System.out.println(adminNoticeList);

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

	// 5-5 공지사항 게시글 선택 삭제

	@PostMapping(value = "/adminNotice/deleteNoticeList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public int deleteNoticeList(@RequestBody Map<String, Integer> request) {

		int noticeNo = request.get("noticeNo");
		
		return service.noticeDelete(noticeNo);
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
	public String qnaRead(Model model, @PathVariable(value = "qnaNo", required = false) int qnaNo,Qna qna,
			QnaComment qnaComment) {

		qna = service.selectQnaOne(qnaNo);
		
		QnaComment qnaCommentList = service.selectQnaCommentList(qnaComment);

		qna.setQnaNo(qnaNo);
		model.addAttribute("Qna", qna);
	
		if (qnaCommentList != null) {
		    model.addAttribute("QnaComment", qnaCommentList);
		} else {
			
		    // qnaCommentList가 null인 경우에 대한 처리 로직 추가
		    // 예: 필요한 필드들을 초기화하거나 기본값으로 설정
		    QnaComment emptyQnaComment = new QnaComment();
		    model.addAttribute("QnaComment", emptyQnaComment);
		}	
		return "admin/admin_QNA_read";
	}

	// 6-2. 1:1 문의사항 게시글 쓰기 230613
	@GetMapping("/adminQnaWrite") //
	public String qnaWrite() {

		return "admin/admin_QNA_write";
	}

	// 6-2-1. 1:1 문의사항 게시글 쓰기 - 삽입 230614

	@GetMapping("/adminQnaWriteInsert")
	public String qnaWriteIinsert(Qna qna, Model model) {

		int qnaNo = service.qnaInsert(qna);

		model.addAttribute("Qna", qna);

		return "admin/admin_QNA_read";
	}
	// 6-2-1. 1:1 문의사항 답변 게시글 쓰기 - 삽입 230615------------------------------------------------진행중6/22

	@RequestMapping("/adminQnaAnswer/{qnaNo}")
	public String qnaAnswerInsert(Qna qna, Model model, @PathVariable(value = "qnaNo") int qnaNo,
	        @RequestParam(value = "qnaComment", required = false) String qnaComment,
	        @RequestParam(value = "userNo", required = false) String userNo,
	        @RequestParam(value = "qnaCommentNo", required = false) String qnaCommentNo,
	        @ModelAttribute QnaComment qnaCommentAll, RedirectAttributes ra) {
		
		System.out.println("qnaCommentNo : " + qnaCommentNo);
		System.out.println("userNo : " + userNo);
		System.out.println("qnaNo : " + qnaNo);
		System.out.println("qnaComment : " + qnaComment);
		

	    if (qnaComment != null) {
			System.out.println("	    if (qnaComment != null) 통과 ");
	    	QnaComment qnaCommentObj = new QnaComment();
	    	qnaCommentObj.setQnaNo(qnaNo);
	    	qnaCommentObj.setQnaComment(qnaComment);
	    	qnaCommentObj.setUserNo(qnaNo);
//	    	qnaCommentObj.setQnaCommentNo(qnaCommentNo);
	    	
//	    	if (userNo < ) {
//	    	    qnaCommentObj.setUserNo(userNo);
//	    	}
	    	
	    	qnaCommentObj.setQnaCommentNo(qnaCommentAll.getQnaCommentNo());
	        
	        int qnaUpdateResult = service.qnaAnswerUpdate(qnaCommentObj);

	        System.out.println(qnaUpdateResult);
	        System.out.println(qnaCommentObj);

	        if (qnaUpdateResult > 0) {
	            ra.addFlashAttribute("message", "성공");
	        } else {
	            ra.addFlashAttribute("message", "실패");
	        }
	    } else {
	      
	        QnaComment qnaCommentObj = new QnaComment();
	        qnaCommentObj.setQnaNo(qnaNo);
	        qnaCommentObj.setQnaComment(qnaComment);
	      
	        // 결과값 (0,1)
	        int qnaResult = service.qnaAnswerInsert(qnaCommentObj);

	        // 결과(update)
	        // QnaComment qnaFlUpdate = service.updateAnswer(qnaComment);

	        System.out.println(qnaResult);

	        if (qnaResult > 0) {
	            ra.addFlashAttribute("message", "성공");
	        } else {
	            ra.addFlashAttribute("message", "실패");
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

	// 6-4 1:1 문의 게시글 선택 삭제

	@PostMapping(value = "/adminQna/deleteQnaList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public int deleteQnaList(@RequestBody Qna qna) {

		return service.qnaDelete(qna.getQnaNo());
	}

	// 6-5 1:1 문의 게시글 검색
	@GetMapping("/getSearchList")
	public String getSearchList(@Param("type") String type, @Param("keyword") String keyword, Model model) {

		Qna qnaList = new Qna();
		qnaList.setType(type);
		qnaList.setKeyword(keyword);

		model.addAttribute("qna", qnaList);
		System.out.println(qnaList);

		List<Qna> qna = service.getSearchList(qnaList);
		model.addAttribute("adminQnaList", qna); // 수정: qna 변수를 모델에 추가

		return "admin/admin_QNA";

	}

	// 7. FAQ 리스트 조회
	@GetMapping("/adminFaq") //
	public String faqList(Model model) {

		List<FAQ> adminFaqList = service.adminFaqList();

		model.addAttribute("adminFaqList", adminFaqList);

		return "admin/admin_FAQ";
	}

	// 7-1. FAQ 게시글 조회
	@GetMapping("/adminFaqRead/{FAQNo}")
	public String faqReadModel(Model model, @PathVariable(value = "FAQNo", required = false) int FAQNo, FAQ faq) {

		List<FAQ> adminFaqOne = service.adminFaqOne(faq);

		model.addAttribute("Faq", adminFaqOne);

		return "admin/admin_FAQ_read";
	}

	
	  // 7-2. FAQ 게시글 쓰기 화면
	  
	  @GetMapping("/adminFaqWrite") public String faqWrite() {

	  return "admin/admin_FAQ_Write"; 
	
}
	// 7-2-1. 1:1 문의사항 게시글 쓰기 - 삽입 230614

	@PostMapping("/adminFaqWriteInsert")
	public String faqWriteIinsert(FAQ faq, Model model) {

		int FAQNo = service.faqInsert(faq);

		model.addAttribute("Faq", faq);

		return "admin/admin_FAQ_read";
}
	  
	 

	// 7-2. FAQ게시글 수정 불러오기 (select)
	@GetMapping("/adminFaqUpdate/{FAQNo}")
	public String faqUpdate(Model model, @PathVariable(value = "FAQNo", required = false) int FAQNo, FAQ faq) {

		List<FAQ> adminFaqList = service.adminFaqOne(faq);
		faq.setFAQNo(FAQNo);

		model.addAttribute("Faq", adminFaqList);
		System.out.println(adminFaqList);

		return "admin/admin_FAQ_update";
	}

	// 7-2-1.FAQ게시글 수정 불러오기 (update)

	@PostMapping("/adminFaqUpdate/{FAQNo}")
	public String faqUpdate(FAQ faq, Model model, @PathVariable(value = "FAQNo") int FAQNo, RedirectAttributes ra) {

		int faqNoCheck = service.updateFaq(faq);
		FAQ faqList = new FAQ();

		model.addAttribute("Faq", faqList);
		System.out.println(faq);
		System.out.println(faqList);

		// 삽입 성공 시
		String message = null;
		String path = "redirect:";
		if (faqNoCheck > 0) { // 성공시

			message = "게시글이 등록 되었습니다.";
			path += "/adminFaqRead" + "/" + FAQNo;

		} else {
			message = "게시글이 등록 실패 되었습니다.";
			path += "adminFaqupdate";
		}

		ra.addFlashAttribute("message", message);
		return path;

	}

	// 7-3. FAQ 게시글 삭제
	@GetMapping("/adminFaqDelete/{FAQNo}") //
	public String faqDelete(FAQ faq, Model model, @PathVariable(value = "FAQNo") int FAQNo, RedirectAttributes ra) {

		int faqNoCheck = service.deleteFaq(faq);
		FAQ faqList = new FAQ();

		model.addAttribute("Faq", faqList);
		System.out.println(faq);
		System.out.println(faqList);

		// 삽입 성공 시
		String message = null;
		String path = "redirect:";
		if (faqNoCheck > 0) { // 성공시

			message = "게시글이 등록 되었습니다.";
			path += "/adminFaq";

		} else {
			message = "게시글이 등록 실패 되었습니다.";
			path += "adminFaqupdate";
		}

		ra.addFlashAttribute("message", message);
		return path;

	}

	// 7-3-1 FAQ 게시글 선택 삭제

	@PostMapping(value = "/adminFaq/deleteFaqList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public int deleteFaqList(@RequestBody Map<String, Integer> request) {
	    int FAQNo = request.get("FAQNo");
	    System.out.println(FAQNo);

	    return service.deleteFaq(FAQNo);
	}

	// 7-4.공지사항 게시글 검색
	@GetMapping("/getFaqSearchList")
	public String getFaqeSearchList(@Param("type") String type, @Param("keyword") String keyword, Model model) {

		FAQ FaqList = new FAQ();
		FaqList.setType(type);
		FaqList.setKeyword(keyword);

		List<FAQ> adminFaqList = service.getFaqSearchList(FaqList);
		model.addAttribute("adminFaqList", adminFaqList);
		
		System.out.println(FaqList);
		System.out.println(adminFaqList);

		return "admin/admin_faq";

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
