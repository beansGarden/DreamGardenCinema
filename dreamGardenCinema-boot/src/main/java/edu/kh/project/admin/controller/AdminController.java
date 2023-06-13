package edu.kh.project.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.project.admin.model.service.AdminService;
import edu.kh.project.qna.model.dto.Qna;


@Controller
public class AdminController {
	
	@Autowired
	private AdminService service;

	//1.관리자 메인 대시보드
	@GetMapping("/admin")
	public String dashboard() {
	
		return "admin/admin_dashboard";
	}
	
	//2.관리자 회원 관리
	@GetMapping("/adminUser")//
	public String adminUser() {
	
		
		return "admin/admin_user";
	}
	
	
	//3.관리자 영화 관리
	@GetMapping("/adminMovieManage")//
	public String movieManage() {
	
		
		return "admin/admin_movieManage";
	}
	
	//3-1.관리자 영화 등록
	 @GetMapping("/adminMovieRegister")//
		public String movieRegister() {
		
			
			return "admin/admin_movieManageDetail";
		}
		
	
	//4.관리자 상영 관리
		@GetMapping("/adminCinemaManage")//
		public String cinemaManage() {
		
			
			return "admin/admin_cinemaManage";
		}
		
		//4-1.관리자 상영 시간 등록
		@GetMapping("/adminCinemaRegister")//
				public String cinemaRegister() {
				
					
					return "admin/admin_cinemaMangeDetail";
				}
				
		//5.관리자 공지사항 리스트 조회
		@GetMapping("/adminNotice")//
		public String notice() {
					
						
		return "admin/admin_notice";
		}
		
		//5-1.공지사항 게시글 조회
		@GetMapping("/adminNoticeRead")//
		public String noticeRead() {
					
						
		return "admin/admin_notice_read";
		}
		
		
		
		//5-2. 공지사항 게시글 쓰기
		@GetMapping("/adminNoticeWrite")//
		public String noticeWrite() {
					
						
		return "admin/admin_notice_write";
		}
		
		
		//5-3. 공지사항 게시글 수정
		@GetMapping("/adminNoticeUpate")//
		public String noticeUpdate() {
					
						
		return "admin/admin_notice_update";
		}
		
		
		//5-4. 공지사항 게시글 삭제  --- 페이지 없음
		@GetMapping("/adminNoticeDelete")//
		public String noticeDelete() {
					
						
		return "admin/admin_notice";
		}
		
		
		//6. 1:1 문의사항 리스트 조회 230613
		@GetMapping("/adminQna")//
		public String qnaList(Model model) {
		
		List<Qna> adminQnaList = service.adminQnaList();
				
		model.addAttribute("adminQnaList", adminQnaList);
					
		return "admin/admin_QNA";
		}
		
		
		
		//6-1. 1:1 문의사항 게시글 조회
		@GetMapping("/adminQnaRead/{qnaNo}")//
		public String qnaRead(Model model,@PathVariable(value="qnaNo",required=false) int qnaNo) {
					
			Qna qna = service.selectQnaOne(qnaNo);
				
			qna.setQnaNo(qnaNo);
			model.addAttribute("Qna", qna);
			
		return "admin/admin_QNA_read";
		}
		
		//6-2. 1:1 문의사항 게시글 쓰기
		@GetMapping("/adminQnaWrite")//
		public String qnaWrite() {
					
						
		return "admin/admin_QNA_write";
		}
		
		
		//6-2. 1:1 문의사항 게시글 수정
		@GetMapping("/adminQnaUpdate")//
		public String qnaUpdate() {
					
						
		return "admin/admin_QNA_update";
		}
		
		//6-3. 1:1 문의사항 게시글 삭제
		@GetMapping("/adminQnaDelete")//
		public String qnaDelete() {
					
						
		return "admin/admin_QNA";
		}
		
		//7. FAQ 리스트 조회
		@GetMapping("/adminFaq")//
		public String faqList() {
					
						
		return "admin/admin_FAQ";
		}
		
		//7-1. FAQ 게시글 조회
		@GetMapping("/adminFaqRead")//
		public String faqRead() {
					
						
		return "admin/admin_FAQ_read";
		}
				
		//7-2. FAQ 게시글 쓰기
		@GetMapping("/adminFawWrite")//
		public String faqWrite() {
					
						
		return "admin/admin_FAQ_Write";
		}
				
		//7-2. FAQ게시글 수정
		@GetMapping("/adminFaqUpdate")//
		public String faqUpdate() {
					
						
		return "admin/admin_FAQ_update";
		}
				
		//7-3. FAQ 게시글 삭제
		@GetMapping("/adminFaqDelete")//
		public String faqDelete() {
					
						
		return "admin/admin_FAQ_Delete";
		}
	
		//8. 신고하기 리스트 조회
		@GetMapping("/adminReport")//
		public String report() {
					
						
		return "admin/admin_report";
		}
		@GetMapping("/adminMain")//
		public String main() {
					
						
		return "admin/admin_main";
		}
		
		//8-1. 신고하기 게시글 조회
						
		//8-2. 신고하기 게시글 쓰기
						
		//8-2. 신고하기 게시글 수정
						
		//8-3. 신고하기 게시글 삭제
		
}
