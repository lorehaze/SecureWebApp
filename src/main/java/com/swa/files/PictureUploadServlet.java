package com.swa.files;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
			PrintWriter printWriter = response.getWriter();

			if (flag == true) {
				response.sendRedirect("upload.jsp");
			} else {
				RequestDispatcher errDispatcher = request.getRequestDispatcher("login.jsp");
				errDispatcher.include(request, response);
				printWriter.print("<br><h6>Session has expired.<br>Please, login now!</h6>");
			}
		}
	}
}
