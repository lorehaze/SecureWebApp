package com.swa.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swa.session.SessionManagement;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cookie[] cookies = request.getCookies();

		SessionManagement sessionman = new SessionManagement();

		boolean flag = sessionman.CheckSession(cookies);

		if (flag == true) {
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(request.getSession().getAttribute("email"))) {
						System.out.println(request.getSession().getAttribute("email") + cookie.getValue());
					}
					cookie.setMaxAge(0);
					cookie.setValue("");
					response.addCookie(cookie);
				}
			}
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("login.jsp");
		}
	}
}
