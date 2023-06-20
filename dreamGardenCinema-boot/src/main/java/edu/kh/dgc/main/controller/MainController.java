package edu.kh.dgc.main.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.service.MovieService;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.notice.model.service.NoticeService;
import edu.kh.dgc.ticketing.model.service.TicketingService;

@Controller
public class MainController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping("/")
	public String mainForward(Model model) {
		
		// 현재 상영작 정보 얻어오기
		List<Movie> movieList = movieService.selectMovieListCurrent();
		
		
		List<Notice> noticeList = noticeService.selectNoticeList();
		
		// 메인슬라이더 이미지 얻어오기
		List<Map<String, String>> MainSlideImgList = movieService.selectMainSlideImgList();
		
		// 광고용 포스터 이미지 얻어오기
		Map<String, String> advertisePoster = movieService.selectAdvertisePoster();

		model.addAttribute("movieList", movieList);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("movieMainSlideImgList", MainSlideImgList);
		model.addAttribute("advertisePoster", advertisePoster);

		return "common/main";
	}
	
}
