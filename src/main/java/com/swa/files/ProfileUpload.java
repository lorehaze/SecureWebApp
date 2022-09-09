package com.swa.files;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.swa.dbconnection.Database;
import com.swa.session.SessionManagement;
import com.swa.user.UserDao;
import com.swa.user.UserBean;

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

		response.setContentType("text/html");
		Cookie[] cookies = request.getCookies();

		SessionManagement sessionman = new SessionManagement();

		boolean flag = sessionman.CheckSession(cookies);
		String email = null;

		if (flag == true) {
			email = sessionman.retrieveEmail(cookies);

			UserBean user = new UserBean();

			user.setEmail(email);

			//System.out.println("email: " + user.getEmail());

			UserDao dao = new UserDao();

			int user_id = dao.getID(user);

			InputStream inputStream = null;// input stream of uploaded file
			Part part = request.getPart("photo");
			if (part != null) {
				//System.out.println(part.getName());
				//System.out.println(part.getSize());
				//System.out.println(part.getContentType());
				inputStream = part.getInputStream();
			}
			// Now Create a connection and send it to DB...
			Connection conn = Database.getConn_update();
			// String sql = "INSERT INTO user (profile_photo) VALUES (?) WHERE user_id = 1";
			String sql = "UPDATE user SET profile_photo = ? WHERE user_id = ?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setBlob(1, inputStream);
				ps.setInt(2, user_id);
				int i = ps.executeUpdate();
				System.out.println(i);
				if (i > 0) {
					request.setAttribute("success", "Picture Added Successfully");
					request.getRequestDispatcher("profile.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("index.jsp");
		}
	}
}