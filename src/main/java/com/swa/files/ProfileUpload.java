package com.swa.files;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.swa.dbconnection.Database;

/**
 * Servlet implementation class PetServlet
 */
@MultipartConfig(maxFileSize = 16177215)
public class ProfileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		InputStream inputStream = null;// input stream of uploaded file
		Part part = request.getPart("photo");
		if (part != null) {
			System.out.println(part.getName());
			System.out.println(part.getSize());
			System.out.println(part.getContentType());
			inputStream = part.getInputStream();
		}
		// Now Create a connection and send it to DB...
		Connection conn = Database.getConn_update();
		// String sql = "INSERT INTO user (profile_photo) VALUES (?) WHERE user_id = 1";
		String sql = "UPDATE user SET profile_photo = ? WHERE user_id = 1";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBlob(1, inputStream);
			int i = ps.executeUpdate();
			if (i > 0) {
				request.setAttribute("success", "Picture Added Successfully");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}