package com.swa.files;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swa.session.SessionManagement;

/**
 * Servlet implementation class PictureUploadServlet
 */
public class PictureUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PictureUploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("Action").equals("UploadPicture")) {

			Cookie[] cookies = request.getCookies();

			SessionManagement sessionman = new SessionManagement();

			boolean flag = sessionman.CheckSession(cookies);

			if (flag == true) {
				response.sendRedirect("profile.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("Action").equals("UploadPicture")) {

			Cookie[] cookies = request.getCookies();

			SessionManagement sessionman = new SessionManagement();

			boolean flag = sessionman.CheckSession(cookies);

			if (flag == true) {
				response.sendRedirect("upload.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	}
}
