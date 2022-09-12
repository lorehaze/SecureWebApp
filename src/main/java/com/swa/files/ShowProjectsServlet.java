package com.swa.files;

import java.io.File;
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
 * Servlet implementation class ShowProjectsServlet
 */
public class ShowProjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String filePath = "/Users/lorenzo/Documents/GitHub/SecureWebApp/src/main/webapp/uploads/";
	private String relPath = "/SecureWebApp/uploads/";
	private String upperTable = "<table class=\"table table-hover thread-light\" align=\"center\" cellspacing=\"0\" width=\"100%\">";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowProjectsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		Cookie[] cookies = request.getCookies();
		SessionManagement sessionman = new SessionManagement();
		boolean flag = sessionman.CheckSession(cookies);
		PrintWriter printWriter = response.getWriter();

		if (flag == true) {
			File folder = new File(filePath);
			URLBuilder builder = new URLBuilder();
			RequestDispatcher dispatcher = request.getRequestDispatcher("listFiles.jsp");
			dispatcher.include(request, response);
			File[] listOfFiles = folder.listFiles();
			printWriter.print(upperTable);
			for (File file : listOfFiles) {
				if (file.isFile()) {
					String NameFile = file.getName();
					String fileLink = builder.buildURL(relPath, NameFile);
					// printWriter.println("<center>" + NameFile + "</center>");
					printWriter.print("<tr><td>");
					printWriter.print(fileLink);
					printWriter.print("</td><tr>");
				}
			}
			printWriter.print("</table>");
		} else {
			RequestDispatcher errDispatcher = request.getRequestDispatcher("login.jsp");
			errDispatcher.include(request, response);
			printWriter.print("<br><h6>Session has expired.<br>Please, login now!</h6>");
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
