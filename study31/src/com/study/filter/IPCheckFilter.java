package com.study.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

public class IPCheckFilter implements Filter {
	
	private Map<String, String> denyIPMap;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 초기화
		denyIPMap = new HashedMap();
		// 거부 아이피
		denyIPMap.put("192.168.20.35" , "Critical");
		denyIPMap.put("192.168.20.35" , "Normal");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if(denyIPMap.containsKey(request.getRemoteAddr())) {
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<h2>접속거부</h2>");
			out.print("<p>현재 접속하신 IP("+ request.getRemoteAddr() +") 는 거부된 아이피입니다</p>"); 
			out.print("<p>문의사항이 있으시면 전화 : 042-719-8850으로 연락주세요</p>");
		} else {
        	chain.doFilter(request, response);
        }	
	}
}
