package edu.kh.dgc.movie.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.service.MovieService;

@RequestMapping("/movie")
@Controller
public class MovieController {

	@Autowired
	private MovieService service;
	
	private int currentPage = 0;
	
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
		
		List<Movie> MovieListCurrent = service.selectMovieListCurrent(currentPage);
		
		
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
		
		List<Movie> MovieListPromise = service.selectMovieListPromise(currentPage);
		
		
		model.addAttribute("movieMainSlideImgList", movieMainSlideImgList);
		model.addAttribute("MovieListP", MovieListPromise);
		model.addAttribute("advertisePoster", advertisePoster);
		
		return "movie/movieListPromise";
	}
	
	@PostMapping("/list")
	@ResponseBody
	public List<Movie> ajaxList(@RequestBody Map<String,String> data) {
		
		int currentPage = Integer.parseInt(data.get("currentPage"));
		String movieType = data.get("movieType");
		
//		System.out.println(currentPage + " + " +  movieType + " + " + buttonType);
		
		return service.selectMovieList(currentPage, movieType);
	}
	
	@GetMapping("/movieDetail={movieNo}")
	public String selectMovieDetail(@PathVariable("movieNo") int movieNo,
									Model model) {
		
		Movie movieInfo = service.selectMovieDetail(movieNo);
		
		Map<String, String> advertisePoster = service.selectAdvertisePoster();
		
		model.addAttribute("movie", movieInfo);
		model.addAttribute("advertisePoster", advertisePoster);
		
		return "movie/movieDetail";
	}
	
	
}
