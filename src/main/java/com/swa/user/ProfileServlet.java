package com.swa.user;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProfileServlet
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * response.setContentType("text/html");
		 * 
		 * Cookie ck[] = request.getCookies();
		 * 
		 * if (Arrays.stream(ck).anyMatch("email"::equals)) { String email =
		 * ck[1].getValue(); if (email.contains("@")) {
		 * response.sendRedirect("profile.jsp"); } else {
		 * response.sendRedirect("login.jsp"); } } else {
		 * response.sendRedirect("login.jsp"); }
		 */
		/*response.setContentType("text/html");
		Cookie ck[] = request.getCookies();
		String email=null;
		if (ck != null) {
			email = ck[1].getValue();
			if (email.contains("@")) {
				response.sendRedirect("profile.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}
		}*/
		response.sendRedirect("profile.jsp");
	}
}
