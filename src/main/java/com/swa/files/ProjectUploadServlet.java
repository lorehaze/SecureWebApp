package com.swa.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.swa.session.SessionManagement;

/**
 * Servlet implementation class ProjectUploadServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProjectUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String UPLOAD_DIRECTORY = "/Users/lorenzo/Documents/GitHub/SecureWebApp/src/main/webapp/uploads/";
	private static final String FILE_PATTERN = "\\b(.txt)\\b";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectUploadServlet() {
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

		Cookie[] cookies = request.getCookies();

		SessionManagement sessionman = new SessionManagement();

		boolean flag = sessionman.CheckSession(cookies);

		if (flag == true) {

			Part filePart = request.getPart("fileToUpload");
			InputStream fileInputStream = filePart.getInputStream();
			File fileToSave = new File("/Users/lorenzo/Documents/GitHub/SecureWebApp/src/main/webapp/uploads/"
					+ filePart.getSubmittedFileName());
			Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

			response.sendRedirect("upload.jsp");
		} else {
			response.sendRedirect("login.jsp");
		}

		// get the file chosen by the user
		// Part filePart = request.getPart("fileToUpload");

		// get the InputStream to store the file somewhere

		// for example, you can copy the uploaded file to the server
		// note that you probably don't want to do this in real life!
		// upload it to a file host like S3 or GCS instead

		// get the URL of the uploaded file
		// String fileUrl = "http://localhost:8080/uploaded-files/" +
		// filePart.getSubmittedFileName();

		// You can get other form data too
		// String name = request.getParameter("name");

		// create output HTML that uses the
		// response.getOutputStream().println("<p>Thanks " + name + "! Here's a link to
		// your uploaded file:</p>");
		// response.getOutputStream().println("<p><a href=\"" + fileUrl + "\">" +
		// fileUrl + "</a></p>");
		// response.getOutputStream().println("<p>Upload another file <a
		// href=\"http://localhost:8080/index.html\">here</a>.</p>");
	}
}
