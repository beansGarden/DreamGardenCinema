package edu.kh.dgc.main.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.dgc.main.model.service.MainService;
import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.service.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	private MainService service;
	
	@Autowired
	private MovieService service2;
	
	@RequestMapping("/")
	public String mainForward(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		Map<String, Object> map = service.selectMovieList();
		
		// 메인슬라이더 이미지 얻어오기
		List<Map<String, String>> MainSlideImgList = service.selectMainSlideImgList();
		
		// 서브슬라이더에 반영될 영화 정보 얻어오기
		List<Movie> MovieListCurrent = service2.selectMovieListCurrent();
		
		// 광고용 포스터 이미지 얻어오기
		Map<String, String> advertisePoster = service2.selectAdvertisePoster();
		
		session.setAttribute("movieList", MovieListCurrent);
		session.setAttribute("noticeList", map.get("noticeList"));
		
		// 이미지 및 영화정보 프론트로 정보 보내기
		model.addAttribute("movieMainSlideImgList", MainSlideImgList);
		model.addAttribute("advertisePoster", advertisePoster);
		
		return "common/main";
	}
	
}
