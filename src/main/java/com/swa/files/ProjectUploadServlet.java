package com.swa.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.swa.session.SessionManagement;
import com.swa.files.ContentExtraction;

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
		PrintWriter printWriter = response.getWriter();
		RequestDispatcher dispatcher = request.getRequestDispatcher("projectUpload.jsp");


		if (flag == true) {
			// get file
			int maxSize = 1024 * 1024 * 5 * 5;
			String contentType = "text/plain";
			Part filePart = request.getPart("fileToUpload");
			InputStream fileInputStream = filePart.getInputStream();
			int fileInputStreamSize = fileInputStream.available(); // get file size
			System.out.println("File size: " + fileInputStreamSize);

			if (fileInputStreamSize <= maxSize) {
				// start verifying
				ContentExtraction checker = new ContentExtraction();
				checker.FileChecker(fileInputStream, contentType); // check if filetype is correct
				File fileToSave = new File(UPLOAD_DIRECTORY + filePart.getSubmittedFileName());
				Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
				dispatcher.include(request, response);
				printWriter.print("<br><h2>File successfully uploaded!<h2>");
				//response.sendRedirect("profile.jsp");
			} else {
				fileInputStream.close();
				dispatcher.include(request, response);
				printWriter.print("File exceed the maximum size!");
			}

		} else {
			response.sendRedirect("login.jsp");
		}
	}
}
