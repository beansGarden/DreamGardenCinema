	package edu.kh.dgc.admin.controller;

import java.util.HashMap;
import java.util.List;
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
	
	
}
