package com.swa.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
			PrintWriter printWriter = response.getWriter();
			UserBean user = new UserBean();
			UserDao dao = new UserDao();
			user.setEmail((request.getParameter("email")));
			user.setPassword(request.getParameter("password").getBytes());
			boolean alredyRegistered = dao.userAlredyRegistered(user);
			System.out.println(alredyRegistered);

			if (alredyRegistered != true) {	// if user isn't alredy registered
				boolean result = dao.addUser(user);
				int id_user = 0; // used to set foreign key with the assigned user
				id_user = dao.getID(user); // getting user ID
				boolean final_res = dao.insertPassword(user, id_user);
				System.out.println("User Added");
				RequestDispatcher dispatcher = request.getRequestDispatcher("addUser.jsp");
				dispatcher.include(request, response);
				printWriter.print("<br><h4>User successfully registered!!</h4>");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("addUser.jsp");
				dispatcher.include(request, response);
				printWriter.print("<br><h4>User alredy exists!!</h4>");
			}

		}
	}
}
