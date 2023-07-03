package edu.kh.dgc.movie.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.dgc.movie.model.dto.MovieComment;
import edu.kh.dgc.movie.model.service.MovieCommentService;
import edu.kh.dgc.user.model.dto.User;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MovieCommentController {
	
	@Autowired
	private MovieCommentService service;
	
	@PostMapping("/movieDetail={movieNo}")
	public String insertMovieComment(@PathVariable("movieNo") int movieNo,
									int score,
									String reviewContent,
									HttpServletRequest request,
									@SessionAttribute("loginUser") User loginUser){
		
		
		int userNo = loginUser.getUserNo();
		
		MovieComment comment = new MovieComment();
		
		comment.setMovieNo(movieNo);
		comment.setScore(score);
		comment.setUserNo(userNo);
		comment.setReviewContent(reviewContent);
		
		int result = service.insertMovieComment(comment);
		
		if(result > 0) {
			System.out.println("댓글 작성 성공");
		}else{
			System.out.println("댓글 작성 실패");
		}
		
		String referer = request.getHeader("Referer");
		
		return "redirect:"+ referer;
	}
	
	@PostMapping("/movieDetail/report")
	public String insertMovieCommentReport(int reviewNo,
											int reportedUserNo,
											String reportTitle,
											String reportContent,
											@SessionAttribute("loginUser") User loginUser,
											HttpServletRequest request,
											Model model,
											RedirectAttributes ra) {
		
		Map<String, Object> report = new HashMap<>();
		
		System.out.println(reviewNo);
		System.out.println(reportedUserNo);
		System.out.println(reportTitle);
		System.out.println(loginUser.getUserNo());
		System.out.println(reportContent);
		
		report.put("reviewNo", reviewNo);
		report.put("reportedUserNo", reportedUserNo);
		report.put("reportTitle", reportTitle);
		report.put("reportWriter", loginUser.getUserNo());
		report.put("reportContent", reportContent);
		
		int result = service.insertMovieCommentReport(report);
		String message;
		
		if(result > 0) message = "신고가 접수 되었습니다";
		else			message = "신고 작성이 실패했습니다. 나중에 다시 시도해주세요";
			
		ra.addFlashAttribute("alertMessage", message);
		 
		String referer = request.getHeader("Referer");
		
		return "redirect:"+ referer;
		
	}
	
}
