	package edu.kh.dgc.admin.controller;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.dgc.admin.model.service.AdminMovieManageService;
import edu.kh.dgc.movie.model.dto.Movie;

@Controller
public class AdminMovieManageController {
	
	@Autowired
	private AdminMovieManageService service;
	
		@GetMapping("/adminMovieManage")
		public String movieManage(Model model, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
				@RequestParam Map<String, Object> paramMap,
				@RequestParam(value = "movieday", required = false) String movieNo) {

			if (paramMap.get("key") == null) {

				Map<String, Object> adminMovieMap = service.adminMovieList(cp);

				model.addAttribute("adminMovieList", adminMovieMap);

				System.out.println(adminMovieMap);
			}

			return "admin/admin_movieManage";
		}

		
		@GetMapping("/adminMovieRegister")
		public String movieRegister() {

			return "admin/admin_movieManageDetail";
		}

		
		@GetMapping("/getMovieSearchList")
		public String getMovieSearchList(@Param("type") String type, @Param("keyword") String keyword, Model model,
				@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {

			Movie condition = new Movie();

			condition.setType(type);
			condition.setKeyword(keyword);

			Map<String, Object> adminMovieMap = service.getMovieSearchList(condition, cp);
			model.addAttribute("adminMovieList", adminMovieMap);

			System.out.println(condition);
			System.out.println(adminMovieMap);

			return "admin/admin_movieManage";

		}
		
		
		@ResponseBody
	    @GetMapping("/adminMovieListAjax")
	    public int adminMovieListAjax() {
	        
			
			return service.movieListCount();
	    }
		
}
