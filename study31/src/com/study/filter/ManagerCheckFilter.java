package com.study.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.study.login.vo.UserVO;

public class ManagerCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = ((HttpServletRequest)request).getSession();
		UserVO user = (UserVO)session.getAttribute("USER_INFO");
		if(user == null) {
			((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/member/memberList.wow");
		} else {
			chain.doFilter(request, response);
		}
	}

}
