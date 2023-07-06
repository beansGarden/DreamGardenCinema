package edu.kh.dgc.movie.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.dgc.movie.model.dto.Movie;
import edu.kh.dgc.movie.model.dto.MovieComment;
import edu.kh.dgc.movie.model.dto.Person;
import edu.kh.dgc.movie.model.service.MovieDetailService;
import edu.kh.dgc.movie.model.service.MovieListService;

@RequestMapping("/movie")
@SessionAttributes({"loginUser"})
@Controller
public class MovieDetailController {
	
	@Autowired
	private MovieListService serviceList;
	
	@Autowired
	private MovieDetailService service;
	
	@GetMapping("/movieDetail={movieNo}&screen={screen}")
	public String selectMovieDetail(@PathVariable("movieNo") int movieNo,
									@PathVariable("screen") String screen,
									Model model) {
		
		Movie movieInfo = service.selectMovieDetail(movieNo, screen);
		String story = movieInfo.getSynopsis();
		movieInfo.setSynopsis(story.replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
		
		List<String> movieDirectorName = service.selectMovieDirectorName(movieNo);
		List<String> movieActorName = service.selectMovieActorName(movieNo);
		
		List<String> movieStillCut = service.selectMovieStillCut(movieNo);
		
		List<Person> moviePersons = service.selectMoviePerson(movieNo);
		
		List<MovieComment> movieComment = service.selectMovieComment(movieNo);
		
		Map<String, String> advertisePoster = serviceList.selectAdvertisePoster();
		
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
	
	
	
}
