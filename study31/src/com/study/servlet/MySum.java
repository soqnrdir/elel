package com.study.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MySum extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<h2>");
		out.println("1부터 10까지의 합");
		out.println("</h2>");
		int sum = 0;
		for(int i = 1; i <= 10; i++) {
			sum += i;
			out.println("<li>" + i + "= " + sum + "</li>");
		}
	}
}
