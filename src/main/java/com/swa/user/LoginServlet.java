package com.swa.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swa.user.UserDao;
import com.swa.user.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("Action").equals("Login")) {
			UserBean user = new UserBean();
			UserDao dao = new UserDao();
			int id_user = 0;
			boolean result = false;
			PrintWriter printWriter = response.getWriter();
			user.setEmail((request.getParameter("email")));
			user.setPassword(request.getParameter("password").getBytes());
			id_user = dao.getID(user); // getting user ID

			if (id_user == 0) {
				printWriter.print("<br><h2>User does not exist! <br><br> Please check again.</h2>");
			} else {
				String email = user.getEmail();
				result = dao.login(user, id_user);
				if (result == false) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.include(request, response);
					printWriter.print("<br><h4>Wrong password. <br><br> Please check your password.</h4>");
				} else {
					Cookie ck = new Cookie("email", email);
					ck.setMaxAge(300);
					response.addCookie(ck);
					//printWriter.print("<br>User successfully logged in" + " " + "as" + " " + email);
					response.sendRedirect("profile.jsp");
				}
			}
			Arrays.fill(user.getPassword(), (byte) 0); // empty password array
		}
	}
	
}
