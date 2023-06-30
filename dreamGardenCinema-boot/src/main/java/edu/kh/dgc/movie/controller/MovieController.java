package edu.kh.dgc.movie.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.dto.MovieComment;
import edu.kh.dgc.movie.model.dto.Person;
import edu.kh.dgc.movie.model.service.MovieService;
import edu.kh.dgc.user.model.dto.User;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/movie")
@SessionAttributes({"loginUser"})
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
		String story = movieInfo.getSynopsis();
		movieInfo.setSynopsis(story.replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
		
		List<String> movieDirectorName = service.selectMovieDirectorName(movieNo);
		List<String> movieActorName = service.selectMovieActorName(movieNo);
		
		List<String> movieStillCut = service.selectMovieStillCut(movieNo);
		
		List<Person> moviePersons = service.selectMoviePerson(movieNo);
		
		List<MovieComment> movieComment = service.selectMovieComment(movieNo);
		
		Map<String, String> advertisePoster = service.selectAdvertisePoster();
		
		model.addAttribute("movie", movieInfo);
		
		model.addAttribute("movieDirectorName", movieDirectorName);
		model.addAttribute("movieActorName", movieActorName);
//		
		model.addAttribute("movieStillCut", movieStillCut);
		
		model.addAttribute("persons", moviePersons);
		
		model.addAttribute("movieComment", movieComment);
		
		model.addAttribute("advertisePoster", advertisePoster);
		
		return "movie/movieDetail";
	}
	
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
