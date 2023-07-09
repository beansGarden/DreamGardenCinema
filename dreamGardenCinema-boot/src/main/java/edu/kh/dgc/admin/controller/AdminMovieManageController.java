	package edu.kh.dgc.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.dgc.admin.model.service.AdminMovieManageService;
import edu.kh.dgc.movie.model.dto.Movie;

@Controller
public class AdminMovieManageController {
	
	@Autowired
	private AdminMovieManageService service;
	
	@GetMapping("/adminMovieManage")
	public String movieManage(Model model) {
		
		String screenType = "current";
		int currentPage = 1;
		
		Map<String, Object> requestData = new HashMap<>();
		
		requestData.put("screenType", screenType);
		requestData.put("currentPage", currentPage);
		
		Map<String, Object> responseData = service.selectList(requestData);
		
		model.addAttribute("responseData", responseData);

		return "admin/admin_movieManage";
	}
	
	@PostMapping("/adminMovieManage/selectList")
	@ResponseBody
	public Map<String, Object> selectList(Model model, @RequestBody Map<String,String> data) {
		
		String screenType = data.get("screenType");
		int currentPage = Integer.parseInt(data.get("currentPage"));
		
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("screenType", screenType);
		requestData.put("currentPage", currentPage);
		
		Map<String, Object> responseData = service.selectList(requestData);
		
		return responseData;
	}
	
	@GetMapping("/adminMovieManage/detail")
	public String forwardDetail(int movieNo,
								String type,
								Model model) {
		
		Map<String, Object> resp = service.movieSelectOne(movieNo);
		Movie movieInfo = (Movie)resp.get("movieInfo");
		String movieRating = movieInfo.getRating();
		if(movieRating.equals("/images/common/main/ALL.png")) movieInfo.setRating("전체관람가");
		else if(movieRating.equals("/images/common/main/12세.png")) movieInfo.setRating("만12세이상관람가");
		else if(movieRating.equals("/images/common/main/15세.png")) movieInfo.setRating("만15세이상관람가");
		else if(movieRating.equals("/images/common/main/18세.png")) movieInfo.setRating("청소년관람불가");
		else movieInfo.setRating("관람등급미정");

		
		String movieScreen = movieInfo.getScreening();
		if(movieScreen.equals("W")) movieInfo.setScreening("임시대기");
		if(movieScreen.equals("P")) movieInfo.setScreening("상영예정");
		if(movieScreen.equals("C")) movieInfo.setScreening("현재상영");
		if(movieScreen.equals("D")) movieInfo.setScreening("상영종료");
		
		
		String story = movieInfo.getSynopsis();
		movieInfo.setSynopsis(story.replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
		
		model.addAttribute("resp", resp);
		
		
		return "admin/admin_movieManageDetail";
	}
	
}











