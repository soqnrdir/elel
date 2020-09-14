package com.study.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ApplicationLoader extends HttpServlet {

	// init : 초기화 관련 메서드
	// destroy : 종료시 발생 메서드
	// service : 매 요청시 처리되는 메서드
	//  service <- doGet, doPost, doPut ...
	
	private ServletContext app;
	
	@Override
	public void init() throws ServletException {
		super.init();
		app = getServletContext();
		app.setAttribute("DB.USER", "java");
		app.setAttribute("DB.PASSWORD", "oracle");
		app.setAttribute("DB.URL", "jdbc:oracle:thin:@127.0.0.1:1521:xe");
		app.setAttribute("FILE.PATH", "/home/pc16/upload");
		System.out.println(this.getClass().getSimpleName() + ".init Call.....");
		
	}
}
