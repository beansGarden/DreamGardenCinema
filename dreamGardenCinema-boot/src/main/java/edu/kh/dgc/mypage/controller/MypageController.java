package edu.kh.dgc.mypage.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.dgc.mypage.model.dto.Coupon;
import edu.kh.dgc.mypage.model.service.MypageService;
import edu.kh.dgc.qna.model.dto.Qna;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.ticketing.model.service.PaymentService;
import edu.kh.dgc.ticketing.model.service.TicketingService;
import edu.kh.dgc.user.model.dto.User;

@SessionAttributes({"loginUser"})
@RequestMapping("/myPage")
@Controller
public class MypageController {

	@Autowired
	private MypageService service;
	
	@Autowired
	private PaymentService PaymentService;
	
	@Autowired
	private TicketingService TicketingService;
	
	@GetMapping("/")
	public String reservation(
			Model model
			,@SessionAttribute("loginUser") User loginuser
			) {

		int userNo = loginuser.getUserNo();
		
		List<Ticket> reservationList = service.reservation(userNo);
		
		model.addAttribute("reservationList",reservationList);
		
		return "myPage/my-page-reservation";
	}
	
	
	@GetMapping("/my-page-cancle-reservation")
	public String cancle(
			Model model
			,@SessionAttribute("loginUser") User loginuser
			) {
		
		int userNo = loginuser.getUserNo();
		
		List<Ticket> cancleList = service.cancleReservation(userNo);
		
		model.addAttribute("cancleList",cancleList);
		
		return "myPage/my-page-cancle-reservation";
	}
	
	@GetMapping("/my-page-membership")
	public String membership(
			
			) {
		
		return "myPage/my-page-membership";
	}
	// 쿠폰 조회
	@GetMapping("/my-page-coupon")
	public String coupon(
			Model model
			,@SessionAttribute("loginUser") User loginuser
			) {
		int userNo = loginuser.getUserNo();
		
		List<Coupon> couponList = service.couponList(userNo);
		
		int couponListSize = couponList.size();
		
        model.addAttribute("CouponListSize", couponListSize);
		
		model.addAttribute("CouponList",couponList);
		
		return "myPage/my-page-coupon.html";
	}
	
	//나의 문의 내역
	@GetMapping("/my-page-inquiry")
	public String inquiry(
			Model model,
			@SessionAttribute("loginUser") User loginuser
			,Qna qna
			) {

		int userNo = loginuser.getUserNo();
		
		List<Qna> myQnaList = service.myQnaList(userNo); 
		
		model.addAttribute("myQnaList",myQnaList);
		
		return "myPage/my-page-inquiry";
	}
	
	// 닉네임 변경
	@PostMapping("/change-nickname")
	public String changeNickName(
			@SessionAttribute("loginUser") User loginuser
			,@RequestParam String redirectUrl
			,String userNickname
			,RedirectAttributes ra
			) {
		System.out.println(userNickname);
		
		loginuser.setUserNickname(userNickname);
		
		int result = service.changeNickName(loginuser);
		
		
		System.out.println(loginuser.getUserNickname());
		
		ra.addAttribute("message","닉네임이 변경되었습니다.");
		
		return "redirect:" + redirectUrl;
	}
	
	// 내정보 수정
	@PostMapping("/change-info")
	public String changeInfo(
			User updateUser
			,String[] memberAddress
			,String userEmail
			,String checkPw
			,String userPw
			,RedirectAttributes ra
			,@SessionAttribute("loginUser") User loginUser
			) {
		
		String addr = String.join("^^^", memberAddress);
		
		updateUser.setUserAddress(addr);
		
		updateUser.setUserNo(loginUser.getUserNo());
		
		int userNo = loginUser.getUserNo();
		
		int result = service.changeInfo(updateUser);
		
		String message = null;
		String path = "redirect:";
		
		if(result>0) {
			
			loginUser.setUserEmail(updateUser.getUserEmail());
			loginUser.setUserAddress(updateUser.getUserAddress());
			
			int result2 = service.changePw(checkPw,userNo,userPw);
				
				if(result2>0) {
					message = "회원 정보가 수정되었습니다.";
					path += "/";
				}else {
					message = "현재 비밀번호가 일치하지않습니다.";
					path +="/myPage/";
				}
		}else {
			
			message = "회원 정보 수정 실패";
			path += "/";
		}
		
		ra.addFlashAttribute("message",message);
		
		return path;
	}
	
	// 회원 탈퇴
	@GetMapping("/secession")
	public String secessionUser(
			@SessionAttribute("loginUser") User loginUser
			,RedirectAttributes ra
			,SessionStatus status
			) {

		String message = null;
		String path = "redirect:";
		
		int userNo = loginUser.getUserNo();
		
		int result = service.secessionUser(userNo);
		
		if(result > 0) {
			message = "탈퇴되었습니다.";
			path += "/";
			status.setComplete();
		}else {
			message = "다시 시도해주세요";
			path += "/myPage/";
		}
		
		ra.addFlashAttribute("message",message);
		
		
		return path;
	}
	
	// 예매 취소
	@PostMapping("/cancel")
	public String ticketCancle(
			@SessionAttribute("loginUser") User loginUser
			,RedirectAttributes ra
			,String ticketId
			,String ticketImpId
			,String payAmount
			,String ticketNo
			,Ticket ticket
			) throws Exception {
		
		
		String token = PaymentService.getToken();
		
		String message = null;
		String path = "redirect:";
		
		System.out.println(ticketId);
		System.out.println(ticketImpId);
		System.out.println(payAmount);
		
		LocalDateTime movieTime = service.movieTime(ticketId);
		
		// 취소할 티켓 해당 영화 시간
		System.out.println(movieTime);
		
		// 현재 시간
		LocalDateTime currentTime = LocalDateTime.now();
		// 현재 시간에서 -10분
        LocalDateTime previousTime = currentTime.minus(10, ChronoUnit.MINUTES);
        System.out.println("현재 시간: " + currentTime);
        System.out.println("이전 시간: " + previousTime);
		
        int tNo = Integer.parseInt(ticketNo);
        
        ticket.setTicketNo(tNo);
        
        
        if (movieTime.compareTo(previousTime) < 0) {
            // movieTime은 previousTime보다 이전입니다.
        	// 상영 시작 후(상영 시간 10분 안남음)
        	
        	message = "취소 가능 시간이 지났습니다";
			path += "/myPage/";
        	
            System.out.println("movieTime은 previousTime보다 이전입니다.");
        } else if (movieTime.compareTo(previousTime) > 0 || movieTime.compareTo(previousTime) == 0) {
            // dateTime1은 dateTime2보다 이후입니다.
        	// 아직 상영 10분 전
            String reasonCancellationPayment = "회원 취소";
			PaymentService.payMentCancle(token, ticketImpId, payAmount, reasonCancellationPayment);
			ticket.setReasonCancellationPayment(reasonCancellationPayment);
			int result1 = TicketingService.updateReasonCancellationPayment(ticket);
        	
			if(result1>0) {
				// 취소 티켓 좌석 정보 삭제
				int deleteSeat = TicketingService.deleteSeat(ticketNo);
			}
			
			message = "취소되었습니다";
			path += "/myPage/my-page-cancle-reservation";
            
			System.out.println("movieTime은 previousTime보다 이후입니다.");
        }
        
        ra.addFlashAttribute("message",message);
        
		return path;
	}
}
