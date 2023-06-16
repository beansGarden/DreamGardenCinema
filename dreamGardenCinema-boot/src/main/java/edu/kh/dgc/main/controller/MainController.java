package edu.kh.dgc.main.controller;

import java.util.List;
<<<<<<< HEAD
import java.util.Map;
=======
>>>>>>> origin/찬희

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.notice.model.service.NoticeService;
import edu.kh.dgc.movie.model.service.MovieService;
import edu.kh.dgc.ticketing.model.service.TicketingService;

@Controller
public class MainController {
	
	@Autowired
	private TicketingService ticketingService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping("/")
	public String mainForward(Model model) {
		
		List<Movie> movieList = ticketingService.selectMovieList();
		List<Notice> noticeList = noticeService.selectNoticeList();
		
		// 메인슬라이더 이미지 얻어오기
		List<Map<String, String>> MainSlideImgList = movieService.selectMainSlideImgList();
		
		// 서브슬라이더에 반영될 영화 정보 얻어오기
		List<Movie> MovieListCurrent = movieService.selectMovieListCurrent();
		
		// 광고용 포스터 이미지 얻어오기
		Map<String, String> advertisePoster = movieService.selectAdvertisePoster();

		model.addAttribute("movieList", MovieListCurrent);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("movieMainSlideImgList", MainSlideImgList);
		model.addAttribute("advertisePoster", advertisePoster);

		return "common/main";
	}
	
}
