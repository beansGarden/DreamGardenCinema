package edu.kh.dgc.movie.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.service.MovieService;

@RequestMapping("/movie")
@Controller
public class MovieController {

	@Autowired
	private MovieService service;
	
	@GetMapping("")
	public String forwardMain(Model model) {
		
		// 영화 메인 페이지에 쓰일 imgList 불러오기
		List<Map<String, String>> movieMainSlideImgList = service.selectMovieMainSlideImgList();
		
		// 영화 페이지 광고 포스터 영역에 쓰일 img 불러오기
		Map<String, String> advertisePoster = service.selectAdvertisePoster();
		
		// movie list db에서 불러오기 
		List<Movie> MovieListCurrentMain = service.selectMovieListCurrentMain();
		
		List<Movie> MovieListPromiseMain = service.selectMovieListPromiseMain();
		
		
		// movie list 프론트로 보내기
		model.addAttribute("movieMainSlideImgList", movieMainSlideImgList);
		
		model.addAttribute("MovieListMainC", MovieListCurrentMain);
		
		model.addAttribute("MovieListMainP", MovieListPromiseMain);
		
		model.addAttribute("advertisePoster", advertisePoster);
		
		return "movie/movieMain";
	}
	
	@GetMapping("/current")
	public String forwardCurrent(Model model) {
		
		// 영화 메인 페이지에 쓰일 imgList 불러오기
		List<Map<String, String>> movieMainSlideImgList = service.selectMovieMainSlideImgList();
		
		// 영화 페이지 광고 포스터 영역에 쓰일 img 불러오기
		Map<String, String> advertisePoster = service.selectAdvertisePoster();
		
		List<Movie> MovieListCurrent = service.selectMovieListCurrent();
		
		
		model.addAttribute("movieMainSlideImgList", movieMainSlideImgList);
		model.addAttribute("MovieListC", MovieListCurrent);
		model.addAttribute("advertisePoster", advertisePoster);
		
		return "movie/movieListCurrent";
	}
	
	@GetMapping("/promise")
	public String forwardPromise(Model model) {
		
		// 영화 메인 페이지에 쓰일 imgList 불러오기
		List<Map<String, String>> movieMainSlideImgList = service.selectMovieMainSlideImgList();
		
		// 영화 페이지 광고 포스터 영역에 쓰일 img 불러오기
		Map<String, String> advertisePoster = service.selectAdvertisePoster();
		
		List<Movie> MovieListPromise = service.selectMovieListPromise();
		
		
		model.addAttribute("movieMainSlideImgList", movieMainSlideImgList);
		model.addAttribute("MovieListP", MovieListPromise);
		model.addAttribute("advertisePoster", advertisePoster);
		
		return "movie/movieListPromise";
	}
	
	
}
