package edu.kh.dgc.common.filter;

import java.io.IOException;

import edu.kh.dgc.user.model.dto.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(filterName="AdminFilter",
urlPatterns = {"/admin", "/adminMain", "/admin/*", "/ticketAmount", "/adminUser", "/adminUserOut", "/adminUser/*", "/getUserSearchList", "/adminUserListAjax", "/adminUserInListAjax"
		,"/adminUserOutListAjax", "/adminCinemaManage", "/adminCinemaTimeSelect", "/adminCinemaDeleteTime", "/adminCinemaDelete", "/adminCinemaRegister", "/adminCinemaRegisterinsert", "/adminCinemaListAjax", "/adminCinemaInsert", "/adminNotice"
		, "/adminNoticeDeleted", "/adminNoticeRead/*", "/getNoticeSearchList", "/adminNoticeWrite", "/adminNoticeWriteInsert", "/adminNoticeUpdate/*", "/adminNoticeRead/*", "/adminNotice/*", "/adminNoticeListAjax", "/adminNoticeInListAjax", "/adminNoticeOutListAjax"
		, "/adminQna", "/adminQnaRead/*", "/adminQnaWrite", "/adminQnaWriteInsert", "/adminQnaAnswer/*", "/adminQnaUpdate/*", "/adminQna/*", "/getSearchList", "/adminQnaListAjax", "/adminFaq", "/admin/monthlySalesByYear"})

public class AdminFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("loginUser");
		
		if(user == null || !user.getUserRole().equals("A")) {
			resp.sendRedirect("/");
		} else {
			chain.doFilter(request, response);
		}
	}
}
