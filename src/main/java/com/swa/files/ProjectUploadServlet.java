package com.swa.files;

import java.io.BufferedInputStream;
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

/**
 * Servlet implementation class ProjectUploadServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProjectUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String UPLOAD_DIRECTORY = "/Users/lorenzo/Documents/GitHub/SecureWebApp/src/main/webapp/uploads/";
	private final int maxSize = 2097152; // 2 mb

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
		if (flag == true) {
			String contentType = "text/plain";
			ContentExtraction checker = new ContentExtraction();
			RequestDispatcher dispatcher = request.getRequestDispatcher("projectUpload.jsp");
			Part filePart = request.getPart("fileToUpload");
			InputStream fileInputStream = filePart.getInputStream();
			int fileInputStreamSize = fileInputStream.available(); // get file size
			BufferedInputStream buffStream = new BufferedInputStream(filePart.getInputStream());
			if (fileInputStreamSize <= maxSize) { // check on size
				boolean isTampered = checker.FileChecker(buffStream, contentType);
				System.out.println("IS IT TAMPERED?: " + isTampered);
				if (!isTampered) {
					//System.out.println("IS INPUT STREAM AVAILABLE? " + fileInputStream.available());
					File fileToSave = new File(UPLOAD_DIRECTORY + filePart.getSubmittedFileName());
					Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
					System.out.println("FILE SIZE: " + fileToSave.length());
					boolean isHijacked = checker.fileVerify(fileToSave);
					if (isHijacked) {
						fileToSave.delete();
						dispatcher.include(request, response);
						printWriter.print("<br><h6>File did not pass security checks and was deleted!<h6>");
					} else {
						dispatcher.include(request, response);
						printWriter.print("<br><h5>File successfully uploaded!<h5>");
					}

				} else {
					fileInputStream.close();
					dispatcher.include(request, response);
					printWriter.print("<br><h6>File is tampered and was deleted!<h6>");
				}
			} else { // if file size is under max
				fileInputStream.close();
				dispatcher.include(request, response);
				printWriter.print("<br><h6>File exceed the maximum size!</h6>");
			}

		} else { // else if flag == false
			RequestDispatcher errDispatcher = request.getRequestDispatcher("login.jsp");
			errDispatcher.include(request, response);
			printWriter.print("<br><h6>Session has expired.<br>Please, login now!</h6>");		}
	}
}