package com.swa.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swa.user.UserDao;
import com.swa.user.UserBean;

/**
 * Servlet implementation class MyServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("Action").equals("Add")) {
			System.out.println("in");
			PrintWriter printWriter = response.getWriter();
			UserBean user = new UserBean();
			UserDao dao = new UserDao();
			user.setEmail((request.getParameter("email")));
			boolean result = dao.addUser(user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("addUser.jsp");
			dispatcher.include(request, response);
			printWriter.print("<br><h2>User added Successfully!!</h2>");
		}
	}
}
