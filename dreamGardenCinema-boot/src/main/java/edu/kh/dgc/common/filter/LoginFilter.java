package edu.kh.dgc.common.filter;

import java.io.IOException;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(filterName="loginFilter",
urlPatterns = {"/myPage/*", "/ticketing/pay", "/ticketing/seat", "/ticketing/complete"})
public class LoginFilter implements Filter{

	public void init(FilterConfig fConfig) throws ServletException {
	}
	public void destroy() {
		System.out.println("---이전 로그인 필터 파괴 ---");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("loginUser")== null) {
			resp.sendRedirect("/");
		}
		else {
			chain.doFilter(request, response);
		}
	}
}
