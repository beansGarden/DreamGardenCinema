package edu.kh.dgc.main.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.service.MovieListService;
import edu.kh.dgc.notice.model.dto.Notice;
import edu.kh.dgc.notice.model.service.NoticeService;

@Controller
public class MainController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private MovieListService movieService;
	
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

		LocalDateTime now = LocalDateTime.now();
		
		String formatedNow = now.format(DateTimeFormatter.ofPattern("MM.dd HH:mm 기준"));
		
		model.addAttribute("now", formatedNow);
		
		return "common/main";
	}
	
}
