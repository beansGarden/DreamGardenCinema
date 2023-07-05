package edu.kh.dgc.admin.controller;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.kh.dgc.admin.model.dto.SalesByPeriod;
import edu.kh.dgc.admin.model.service.AdminService;
import edu.kh.dgc.customerservice.model.dto.FAQ;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.qna.model.dto.QnaComment;
import edu.kh.dgc.report.model.dto.Report;
import edu.kh.dgc.review.model.dto.Review;
import edu.kh.dgc.user.model.dto.User;
import jakarta.servlet.http.HttpServletRequest;
import edu.kh.dgc.ticketing.model.dto.Schedule;
import edu.kh.dgc.ticketing.model.dto.Ticket;


@Controller
public class AdminController {

	@Autowired
	private AdminService service;

	// 1.관리자 메인
	// 대시보드----------------------------------------------------------------------------

	@GetMapping("/adminMain") //
	public String main() {

		return "admin/admin_main";
	}

	@GetMapping("/admin")
	public String dashboard(Model model) {

		// 대시보드 영화 리스트 불러오기
		List<Movie> cinemaList = service.cinemaCurrentList();
		model.addAttribute("cinemaList", cinemaList);

		// 대시보드 QNA 최신 5개 보여지기
		List<Qna> adminQnaList5 = service.adminQnaList5();
		model.addAttribute("adminQnaList", adminQnaList5);

		List<SalesByPeriod> salesByPeriod = service.getSalesByDay();
		model.addAttribute("salesByPeriod", salesByPeriod);

		return "admin/admin_dashboard";
	}

	// 영화별 매출 불러오기
	@GetMapping("/ticketAmount")
	@ResponseBody
	public Map<String, Object> getTicketAmount(@RequestParam(value = "movieNo", required = false) String movieNo) {
		Map<String, Object> response = new HashMap<>();

		List<Ticket> ticketAmount = service.ticketList(movieNo);
		response.put("ticketAmount", ticketAmount);

		return response;
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

	// 2.관리자 회원
	// 관리----------------------------------------------------------------------------
	@GetMapping("/adminUser")
	public String adminUser(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {

		if (paramMap.get("key") == null) {

			Map<String, Object> adminUserList = service.adminUserList(cp);

			model.addAttribute("adminUserList", adminUserList);

			System.out.println(adminUserList);
		}

		return "admin/admin_user";
	}

	// 2.관리자 회원 관리 탈퇴한 회원만
	// 보기-------------------------------------------------------------------------
	@GetMapping("/adminUserOut")
	public String adminOutUser(Model model, @Param("type") String type, @Param("keyword") String keyword,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {

		if (paramMap.get("key") == null) {

			User condition = new User();
			condition.setType(type);
			condition.setKeyword(keyword);

			Map<String, Object> adminUserList = service.adminUserOutList(condition, cp);

			model.addAttribute("adminUserList", adminUserList);

			System.out.println(adminUserList);
		}

		return "admin/admin_userOut";
	}

	// 2-1.관리자 회원 선택 탈퇴 시키기
	@PostMapping(value = "/adminUser/deleteUserList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public int deleteUserList(@RequestBody User user) {

		return service.userDelete(user.getUserNo());
	}

	// 2-1-1.관리자 회원 선택 복구 시키기
	@PostMapping(value = "/adminUser/restoreUserList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public int restoreUserList(@RequestBody User user) {

		return service.restoreUserList(user.getUserNo());
	}

	// 2-2.관리자 회원 게시글 검색
	@GetMapping("/getUserSearchList")
	public String getUserSearchList(@Param("type") String type, @Param("keyword") String keyword, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {

		User condition = new User();
		condition.setType(type);
		condition.setKeyword(keyword);

		Map<String, Object> getUserSearchList = service.getUserSearchList(condition, cp);
		model.addAttribute("adminUserList", getUserSearchList);

		System.out.println(condition);
		System.out.println(getUserSearchList);

		return "admin/admin_user";

	}

	// 2-3 회원 전체 개수 가져오기
	@ResponseBody
	@GetMapping("/adminUserListAjax")
	public int adminUserListAjax() {

		return service.userListCount();
	}
	
	
	// 2-4 탈퇴한 회원 전체 개수 가져오기
	@ResponseBody
	@GetMapping("/adminUserInListAjax")
	public int adminUserInListAjax() {
		
		return service.userInListCount();
	}
	
	// 2-4 탈퇴한 회원 전체 개수 가져오기
	@ResponseBody
	@GetMapping("/adminUserOutListAjax")
	public int adminUserOutListAjax() {
		
		return service.userOutListCount();
	}
	
	
	
//	// 3.관리자 영화 관리----------------------------------------------------------------------------
//	@GetMapping("/adminMovieManage")
//	public String movieManage(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
//			@RequestParam Map<String, Object> paramMap,
//			@RequestParam(value = "movieday", required = false) String movieNo) {
//
//		if (paramMap.get("key") == null) {
//
//			Map<String, Object> adminMovieMap = service.adminMovieList(cp);
//
//			model.addAttribute("adminMovieList", adminMovieMap);
//
//			System.out.println(adminMovieMap);
//		}
//
//		return "admin/admin_movieManage";
//	}
//
//	// 3-1.관리자 영화 등록
//	@GetMapping("/adminMovieRegister")
//	public String movieRegister() {
//
//		return "admin/admin_movieManageDetail";
//	}
//
//	// 3-2 영화 검색
//	@GetMapping("/getMovieSearchList")
//	public String getMovieSearchList(@Param("type") String type, @Param("keyword") String keyword, Model model,
//			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
//
//		Movie condition = new Movie();
//
//		condition.setType(type);
//		condition.setKeyword(keyword);
//
//		Map<String, Object> adminMovieMap = service.getMovieSearchList(condition, cp);
//		model.addAttribute("adminMovieList", adminMovieMap);
//
//		System.out.println(condition);
//		System.out.println(adminMovieMap);
//
//		return "admin/admin_movieManage";
//
//	}
//	
//	//3-3 영화 개수 가져오기
//	@ResponseBody
//    @GetMapping("/adminMovieListAjax")
//    public int adminMovieListAjax() {
//        
//		
//		return service.movieListCount();
//    }
//	
	
	// 4.관리자 상영 관리 
//	@GetMapping("/adminCinemaManage") 
//	public String cinemaManage(Model model,@Param("movieday") String movieday,@RequestParam(value="cp", required=false, defaultValue="1") int cp,@RequestParam Map<String, Object> paramMap) {
//		
//		if(paramMap.get("key") == null) {	
//		
//		Movie condition = new Movie();
//			
//		condition.setMovieday(movieday);
//			
//		Map<String, Object> cinemaMap = service.adminCinemaList(condition,cp);
//		
//		model.addAttribute("cinemaList", cinemaMap);
//		
//		System.out.println(cinemaMap);
//		}
//		return "admin/admin_cinemaManage";
//	}

	// 1,2,3관으로 넘어가기
	// @RequestMapping(value="/adminCinemaManage/{movieTheater}", produces =
	// "application/json; charset=UTF-8")
	// @ResponseBody
	// public Map<String, Object> cinema(Model model, @PathVariable(value =
	// "movieTheater", required = false) String movieTheaterNo,
	// @RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
	//
	// Map<String, Object> cinemaMap = service.adminCinemaTwo(movieTheaterNo, cp);
	// return cinemaMap;
	// }

	// 상영관 리스트 조회(찬희)
	@GetMapping("/adminCinemaManage")
	public String selectCinemaList(@RequestParam(value = "cp", required = false, defaultValue = "1") String cp,
			Model model, @RequestParam Map<String, Object> paramMap) {
		// paramMap에는 movieTheater, date, selectOpt(t,d,a) 가 담겨있음

		String date = (String) paramMap.get("date");

		System.out.println(cp);
		System.out.println(paramMap);

		if (date != null && date.equals("")) {
			paramMap.put("date", null);
		}

		Map<String, Object> map = service.selectCinemaList(paramMap, cp);

		model.addAttribute("map", map);

		return "admin/admin_cinemaManage_new";
	}

	// 상영관 세부 시간 조회 AJAX(찬희)
	@ResponseBody
	@PostMapping("/adminCinemaTimeSelect")
	public List<String> selectDetailTime(@RequestBody Map<String, Object> paramMap) {
		List<String> timeList = service.selectDetailTime(paramMap);

		return timeList;
	}

	// 상영관 세부 시간 삭제(찬희)
	@PostMapping("/adminCinemaDeleteTime")
	public String deleteDetailTime(Schedule schedule, String cp, String selectOpt, String date, String theater,
			RedirectAttributes ra) {

		int result = service.deleteDetailTime(schedule);

		String message = null;
		if (result > 0) {
			if (cp.equals("null")) {
				cp = "1";
			}
			ra.addAttribute("cp", cp);
			ra.addAttribute("movieTheater", theater);
			ra.addAttribute("selectOpt", selectOpt);
			ra.addAttribute("date", date);
			message = "삭제되었습니다";
		} else {
			message = "삭제 실패!! 이미 예약된 좌석이 있는지 확인해주세요";
		}

		ra.addFlashAttribute("message", message);

		return "redirect:/adminCinemaManage";
	}
	
	// 체크한 상영정보 삭제하기
	@PostMapping("/adminCinemaDelete")
	public String deleteTotalTime(String formData) {
		System.out.println(formData);
		
		Gson gson = new Gson();
		Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		List<Map<String, String>> dataList = gson.fromJson(formData, type);
		
		System.out.println(dataList);
		
		int result = service.deleteTotalTime(dataList);
		
		return "redirect:/adminCinemaManage";
	}
	

	// 4-1.관리자 상영 시간 등록
	@GetMapping("/adminCinemaRegister")
	public String cinemaRegister(Model model) {

		List<Movie> cinemaList = service.cinemaList();

		model.addAttribute("cinemaList", cinemaList);

		return "admin/admin_cinemaMangeDetail";
	}

	// 4-2.관리자 상영 시간 등록 (insert)
	@GetMapping("/adminCinemaRegisterinsert")
	public String cinemaRegisterinsert(Model model, Movie movie) {

		int result = service.cinemaListInsert(movie);

		return "admin/admin_cinemaMangeDetail";
	}

	// 4-3 상영관 관리자 전체 개수 가져오기
	@ResponseBody
	@GetMapping("/adminCinemaListAjax")
	public int adminCinemaListAjax() {

		return service.movieScheduleListCount();
	}

	public String adminCinemaInsert(Movie movie) {
	    // Movie 객체에서 영화 시간과 날짜를 가져옵니다.
		System.out.println(movie);
	    String movieTime = movie.getMovieTime();
	    LocalDate movieDay = LocalDate.parse(movie.getMovieday());
	    
	    // 날짜와 시간을 조합하기 위해 DateTimeFormatter를 정의합니다.
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    
	    // movieTime을 개별 시간으로 분할하여 배열로 저장합니다.
	    String[] movieTimes = movieTime.split(",");
	    
	    // 각 시간을 영화 날짜와 조합합니다.
	    for (String time : movieTimes) {
	        LocalDateTime combinedDateTime = LocalDateTime.of(movieDay, LocalTime.parse(time));
	        String combinedTime = combinedDateTime.format(formatter);
	        
	        // 조합된 시간을 Movie 객체에 설정합니다.
	        movie.setMovieTime(combinedTime);
	        
	        // 디버깅을 위해 출력합니다.
	        System.out.println(movie);
	        System.out.println(movieTimes);
	        System.out.println(combinedTime);
	        
	        // 영화 정보를 데이터베이스에 추가합니다.
	        int result = service.adminCinemaInsert(movie);
	    }
	    
	    // 영화 관리 페이지로 리다이렉트합니다.
	    return "redirect:/adminCinemaManage";

	}
	

	// 5.관리자 공지사항 리스트
	// 조회----------------------------------------------------------------------------
	@GetMapping("/adminNotice") //
	public String notice(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {

		if (paramMap.get("key") == null) {

			Map<String, Object> adminNoticeMap = service.adminNoticeList(cp);

			model.addAttribute("adminNoticeMap", adminNoticeMap);

		}
		return "admin/admin_notice";
	}
	
	@GetMapping("/adminNoticeDeleted") //
	public String noticeDeleted(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {
		
		if (paramMap.get("key") == null) {
			
			Map<String, Object> adminNoticeMap = service.adminNoticeDeletedList(cp);
			
			model.addAttribute("adminNoticeMap", adminNoticeMap);
			
		}
		return "admin/admin_notice_deleted";
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
	public String getNoticeSearchList(@Param("type") String type, @Param("keyword") String keyword, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {

		Notice condition = new Notice();

		condition.setType(type);
		condition.setKeyword(keyword);

		Map<String, Object> adminNoticeMap = service.getNoticeSearchList(condition, cp);
		model.addAttribute("adminNoticeMap", adminNoticeMap);

		System.out.println(condition);
		System.out.println(adminNoticeMap);

		return "admin/admin_notice";

	}

	// 5-2. 공지사항 게시글 쓰기
	@GetMapping("/adminNoticeWrite")
	public String noticeWrite() {

		return "admin/admin_notice_write";
	}

	// 5-2. 공지사항 게시글 쓰기 - 삽입

	@PostMapping("/adminNoticeWriteInsert")
	public String noticeWriteInsert(Notice notice, Model model, HttpServletRequest request) {

		int result = service.noticeWriteInsert(notice);

		int noticeNo = notice.getNoticeNo();

		System.out.println(noticeNo);

		model.addAttribute("notice", notice);
		
		// 이전 페이지의 URL을 가져옴
	    String referer = request.getHeader("Referer");

	    // 이전 페이지의 URL이 null이 아니면서 "/adminNoticeWrite"를 포함하고 있다면 "/adminNotice" 페이지로 이동
	    if (referer != null && referer.contains("/adminNoticeWrite")) {
	        return "redirect:/adminNotice";
	    }

	    // 이전 페이지의 URL이 없거나 "/adminNoticeWrite"를 포함하지 않는다면 "/adminNoticeRead" 페이지로 이동
	    return "redirect:/adminNoticeRead/" + noticeNo;
	}

	// 5-3. 공지사항 게시글 수정 화면 전환
	@GetMapping("/adminNoticeUpdate/{noticeNo}")
	public String noticeUpdate(Model model, @PathVariable(value = "noticeNo", required = false) int noticeNo,
			Notice notice) {

		List<Notice> adminNoticeList = service.adminNoticeOne(notice);
		notice.setNoticeNo(noticeNo);

		model.addAttribute("adminNoticeList", adminNoticeList);
		System.out.println(adminNoticeList);

		return "admin/admin_notice_update";
	}
	
	
    //5-3-1. 공지사항 게시글 수정 
    @PostMapping("/adminNoticeUpdate/{noticeNo}")
    public String noticeUpdate(Notice notice, Model model, @PathVariable(value = "noticeNo") int noticeNo, RedirectAttributes ra) {
    	
        int result = service.noticeUpdate(notice);
        Notice noticeList = new Notice();
        model.addAttribute("notice", noticeList);
        
        System.out.println("noticeNo : "+ noticeNo);
        System.out.println("notice : "+ notice);
        System.out.println("result : " + result);
        
        // 삽입 성공 시
        String message = null;
        String path = "redirect:";
        if (result > 0) { // 성공시
        	
            message = "게시글이 등록 되었습니다.";
            path += "/adminNoticeRead" + "/" + noticeNo;
        } else {
            message = "게시글이 등록 실패 되었습니다.";
            path += "adminNoticeupdate";
            
        }
        
        ra.addFlashAttribute("message", message);
        return path;
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
	// 5-6 공지사항 게시글 선택 복구
	
	@PostMapping(value = "/adminNotice/restoreNoticeList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public int restoreNoticeList(@RequestBody Map<String, Integer> request) {
		
		int noticeNo = request.get("noticeNo");
		
		System.out.println("noticeNo :"+noticeNo);
		
		return service.noticeRestore(noticeNo);
	}

	// 5-7 공지사항 전체 개수 가져오기
	@ResponseBody
	@GetMapping("/adminNoticeListAjax")
	public int adminNoticeListAjax() {

		return service.noticeListCount();
	}
	
	//5-8 공지사항 삭제 안 한 게시글 수 가져오기
	@ResponseBody
	@GetMapping("/adminNoticeInListAjax")
	public int adminNoticeInListAjax() {
		
		return service.noticeInListCount();
	}
	
	//5-8 공지사항 삭제 한 게시글 수 가져오기
	@ResponseBody
	@GetMapping("/adminNoticeOutListAjax")
	public int adminNoticeOutListAjax() {
		
		return service.noticeOutListCount();
	}
	
	
	

	// 6. 1:1 문의사항 리스트 조회
	// 230613----------------------------------------------------------------------------
	@GetMapping("/adminQna") //
	public String qnaList(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {

		if (paramMap.get("key") == null) {

			Map<String, Object> adminQnamap = service.adminQnaList(cp);

			model.addAttribute("adminQnamap", adminQnamap);

			System.out.println(adminQnamap);

		}
		return "admin/admin_QNA";
	}

	// 6-1. 1:1 문의사항 게시글 조회 230613
	@GetMapping("/adminQnaRead/{qnaNo}") //
	public String qnaRead(Model model, @PathVariable(value = "qnaNo", required = false) int qnaNo, Qna qna,
			QnaComment qnaComment) {

		// 게시글 불러오기
		qna = service.selectQnaOne(qnaNo);

		// 답변 불러오기
		QnaComment qnaCommentList = service.selectQnaCommentList(qnaComment);

		qna.setQnaNo(qnaNo);
		model.addAttribute("Qna", qna);

		System.out.println(qna);

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

	// 6-2. 1:1 문의사항 게시글 및 답변 수정 조회
	@GetMapping("/adminQnaRead/adminQnaAnsweUpdate/{qnaNo}") //
	public String qnaRead2(Model model, @PathVariable(value = "qnaNo", required = false) int qnaNo, Qna qna,
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
		return "admin/admin_QNA_read2";
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
	// 6-2-1. 1:1 문의사항 답변 게시글 쓰기

	@RequestMapping("/adminQnaAnswer/{qnaNo}")
	public String qnaAnswerInsert(Qna qna, Model model, @PathVariable(value = "qnaNo") int qnaNo,
			@RequestParam(value = "qnaComment", required = false) String qnaComment,
			@RequestParam(value = "userNo", required = false) Integer userNo,
			@RequestParam(value = "qnaCommentNo", required = false) Integer qnaCommentNo,
			@ModelAttribute QnaComment qnaCommentAll, RedirectAttributes ra) {

		System.out.println("qnaCommentNo : " + qnaCommentNo);
		System.out.println("userNo : " + userNo);
		System.out.println("qnaNo : " + qnaNo);
		System.out.println("qnaComment : " + qnaComment);

		if (qnaCommentNo > 0) {
			System.out.println("       if (qnaComment != null) 통과 ");
			QnaComment qnaCommentObj = new QnaComment();
			qnaCommentObj.setQnaNo(qnaNo);
			qnaCommentObj.setQnaComment(qnaComment);
			qnaCommentObj.setUserNo(userNo);
			qnaCommentObj.setQnaCommentNo(qnaCommentNo);

			// if (userNo < ) {
			// qnaCommentObj.setUserNo(userNo);
			// }

			int qnaUpdateResult = service.qnaAnswerUpdate(qnaCommentObj);

			System.out.println("qnaUpdateResult : " + qnaUpdateResult);
			System.out.println("qnaCommentObj :" + qnaCommentObj);

			if (qnaUpdateResult > 0) {
				ra.addFlashAttribute("message", "성공");

				int qnaFlUpdate = service.updateAnswer(qnaNo);

				if (qnaFlUpdate > 0) {
					ra.addFlashAttribute("message", "성공");
					return "redirect:/adminQnaRead" + "/" + qnaNo;
				} else {
					ra.addFlashAttribute("message", "실패");
					return "redirect:/adminQnaRead" + "/" + qnaNo;
				}

			} else {
				ra.addFlashAttribute("message", "실패");

			}
		} else {

			QnaComment qnaCommentInsert = new QnaComment();
			qnaCommentInsert.setQnaNo(qnaNo);
			qnaCommentInsert.setQnaComment(qnaComment);
			qnaCommentInsert.setUserNo(userNo);

			// 결과값 (0,1)
			int qnaResult = service.qnaAnswerInsert(qnaCommentInsert);

			System.out.println("qnaCommentInsert :" + qnaCommentInsert);
			System.out.println("qnaResult : " + qnaResult);

			if (qnaResult > 0) {
				ra.addFlashAttribute("message", "성공");

				// 결과(update)
				int qnaFlUpdate = service.updateAnswer(qnaNo);

				if (qnaFlUpdate > 0) {
					ra.addFlashAttribute("message", "성공");
					return "redirect:/adminQnaRead" + "/" + qnaNo;
				} else {
					ra.addFlashAttribute("message", "실패");
					return "redirect:/adminQnaRead" + "/" + qnaNo;
				}

			} else {
				ra.addFlashAttribute("message", "실패");

			}
		}

		return "redirect:/adminQnaRead" + "/" + qnaNo;

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
	public String getSearchList(@Param("type") String type, @Param("keyword") String keyword, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {

		Qna condition = new Qna();

		condition.setType(type);
		condition.setKeyword(keyword);

		Map<String, Object> adminQnaMap = service.getSearchList(condition, cp);
		model.addAttribute("adminQnamap", adminQnaMap);

		System.out.println(condition);
		System.out.println(adminQnaMap);

		return "admin/admin_Qna";

	}

	// 6-6 Qna 전체 개수 가져오기
	@ResponseBody
	@GetMapping("/adminQnaListAjax")
	public int adminQnaListAjax() {

		return service.qnaListCount();
	}

	// 7. FAQ 리스트
	// 조회----------------------------------------------------------------------------
	@GetMapping("/adminFaq") //
	public String faqList(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {

		if (paramMap.get("key") == null) {

			Map<String, Object> adminFaqMap = service.adminFaqList(cp);

			model.addAttribute("adminFaqMap", adminFaqMap);
		}
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

	@GetMapping("/adminFaqWrite")
	public String faqWrite() {

		return "admin/admin_FAQ_Write";

	}
	// 7-2-1. FAQ 게시글 쓰기 - 삽입 230614

	@PostMapping("/adminFaqWriteInsert")
	public String faqWriteIinsert(FAQ faq, Model model) {

		// int FAQNo = service.faqInsert(faq);

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

	// 7-4.FAQ 게시글 검색
	@GetMapping("/getFaqSearchList")
	public String getFaqeSearchList(@Param("type") String type, @Param("keyword") String keyword, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {

		FAQ condition = new FAQ();

		condition.setType(type);
		condition.setKeyword(keyword);

		Map<String, Object> adminFaqMap = service.getFaqSearchList(condition, cp);
		model.addAttribute("adminFaqMap", adminFaqMap);

		return "admin/admin_faq";

	}

	// 7-5 FAQ 전체 개수 가져오기
	@ResponseBody
	@GetMapping("/adminFaqListAjax")
	public int adminFaqListAjax() {

		return service.faqListCount();
	}

	// 8. 신고하기 리스트
	// 조회----------------------------------------------------------------------------
	@GetMapping("/adminReport") //
	public String report(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {

		if (paramMap.get("key") == null) {

			Map<String, Object> adminReportMap = service.adminReportList(cp);

			model.addAttribute("adminReportMap", adminReportMap);
		}
		return "admin/admin_report";
	}

	// 8-1. 신고하기 게시글 조회

	@GetMapping("/adminReportRead/{reviewNo}")
	public String ReportReadModel(Model model, @PathVariable(value = "reviewNo", required = false) int reviewNo,
			Report report) {

		List<Report> adminReportOne = service.adminReportOne(reviewNo);

		model.addAttribute("report", adminReportOne);

		System.out.println(reviewNo);
		System.out.println(adminReportOne);

		return "admin/admin_report_read";
	}

	// 8-2. 신고하기 리뷰 삭제
	@GetMapping("/adminReportDelete/{reportNo}/delete") //
	public String ReportReviewDelete(Review review, Model model, @PathVariable(value = "reportNo") int reportNo,
			@RequestParam(value = "reviewNo", required = false) int reviewNo, RedirectAttributes ra) {

		int result = service.deleteReview(reviewNo);
		Review reviewList = new Review();
		reviewList.setReviewNo(reviewNo);

		model.addAttribute("review", reviewList);
		System.out.println(review);
		System.out.println(reviewNo);
		System.out.println(reviewList);
		System.out.println(result);

		// 삽입 성공 시
		String message = null;
		String path = "redirect:";
		if (result > 0) { // 성공시

			message = "게시글이 등록 되었습니다.";

			path += "/adminReportRead" + "/" + reportNo;

		} else {
			message = "게시글이 등록 실패 되었습니다.";
			path += "adminReport";
		}
		ra.addFlashAttribute("message", message);
		return path;

	}

	// 8-3. 신고하기 게시글 검색
	@GetMapping("/getReportSearchList")
	public String getReportSearchList(@Param("type") String type, @Param("keyword") String keyword, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {

		Report condition = new Report();

		condition.setType(type);
		condition.setKeyword(keyword);

		Map<String, Object> adminReportMap = service.getReportSearchList(condition, cp);
		model.addAttribute("adminReportMap", adminReportMap);

		return "admin/admin_report";

	}

	// 8-4 신고하기 전체 개수 가져오기
	@ResponseBody
	@GetMapping("/adminreportListAjax")
	public int adminreportListAjax() {

		return service.reportListCount();
	}

	// 9-1. 리뷰관리 게시판
	// 조회------------------------------------------------------------------

	@GetMapping("/adminReview")
	public String adminReivew(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {

		if (paramMap.get("key") == null) {

			Map<String, Object> adminReviewMap = service.adminReviewList(cp);

			model.addAttribute("adminReviewMap", adminReviewMap);
		}
		return "admin/admin_review";
	}

	// 9-2 리뷰관리 전체 개수 가져오기
	@ResponseBody
	@GetMapping("/adminReviewListAjax")
	public int adminReviewListAjax() {

		return service.reviewListCount();
	}

	// 9-3. 리뷰관리 검색

	@GetMapping("/getReviewSearchList")
	public String getReviewSearchList(@Param("type") String type, @Param("keyword") String keyword, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {

		Review condition = new Review();

		condition.setType(type);
		condition.setKeyword(keyword);

		Map<String, Object> adminReviewMap = service.getReviewSearchList(condition, cp);
		model.addAttribute("adminReviewMap", adminReviewMap);

		System.out.println(condition);
		System.out.println(adminReviewMap);

		return "admin/admin_review";

	}

    //9-4 리뷰 읽기
    @GetMapping("/adminReviewRead/{reviewNo}") 
    public String adminreviewRead(Model model,@PathVariable(value = "reviewNo", required = false) int reviewNo) {
        List<Review> adminReviewOne = service.adminReviewOne(reviewNo);
        model.addAttribute("adminReviewList", adminReviewOne);
        return "admin/admin_review_read";
    }

	// 9-5. 리뷰관리 게시글 삭제
    @PostMapping(value = "adminReivew/deleteReviewList", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String deleteReviewList(@RequestBody Map<String, Integer> request,Model model,RedirectAttributes ra, Review review,Report report) {
        int reviewNo = request.get("reviewNo");
        
        int result = service.deleteReview(reviewNo);
        Review reviewList = new Review();
        reviewList.setReviewNo(reviewNo);
        
        
        model.addAttribute("reviewList :", reviewList);
        System.out.println("review :" + review);
        System.out.println("reviewNo :" + reviewNo);
        System.out.println("result :" + result);
        
        // 삽입 성공 시
        String message = null;
        String path = "redirect:";
        if (result > 0) { // 성공시
        	
            message = "게시글이 등록 되었습니다.";
            
            //신고글 처리여부 
            int reportNo = request.get("reportNo");
            
            System.out.println("reportNo :" + reportNo);
            
            int result2 = service.updateDeleteReport(reportNo);
            
            if(result2> 0) {
                message = "신고글이 처리 되었습니다,";
                
                path += "redirect:";
            }
            path += "/adminReportRead" +"/" + reportNo;
        } else {
            message = "게시글이 등록 실패 되었습니다.";
            path += "adminReport";
        }
        ra.addFlashAttribute("message", message);
        
        return path;
        
    }   
        // 9-6. 리뷰관리 게시글 선택 복구
        
        @PostMapping(value = "adminReivew/restoreReviewList", produces = "application/json; charset=UTF-8")
        @ResponseBody
        public int restoreReviewList(@RequestBody Map<String, Integer> request) {
            int reviewNo = request.get("reviewNo");
            
            System.out.println(reviewNo);
        	
            return service.restoreReview(reviewNo);
        
        
        
    }
    
    
}
