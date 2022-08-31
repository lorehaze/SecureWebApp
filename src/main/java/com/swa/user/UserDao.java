package com.swa.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.swa.user.UserBean;
import com.swa.dbconnection.Database;
import com.swa.dbconnection.Database;

public class UserDao {

	public boolean addUser(UserBean user) {
		Connection con = Database.getConn_write();
		String sql = "INSERT INTO user (user_id,email) values (NULL,?) ";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (i == 0) {
			return false;
		} else {
			return true;
		}
	}

	public int getID(UserBean user) {
		int userid = 0;

		Connection con = Database.getConn_read();
		String search_id_query = "SELECT `user_id` FROM `user` WHERE email = ? ";
		try {
			PreparedStatement ps = con.prepareStatement(search_id_query);
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userid = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userid;
	}

	public boolean insertPassword(UserBean user, int userID) {
		Connection con = Database.getConn_write();
		String sql = "INSERT INTO password (pass_id,user_id,password) VALUES (NULL,?,?)";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setBytes(2, user.getPassword());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Arrays.fill(user.getPassword(), (byte) 0); // empty password array

		if (i == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean login(UserBean user, int userID) {
		Connection con = Database.getConn_read();
		int passid = 0;

		String login_query = "SELECT pass_id FROM password WHERE user_id=? AND password=?";

		try {
			PreparedStatement ps = con.prepareStatement(login_query);
			ps.setInt(1, userID);
			ps.setBytes(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				passid = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Arrays.fill(user.getPassword(), (byte) 0); // empty password array
		if (passid == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean userAlredyRegistered(UserBean user) {
		boolean isRegistered = false;
		int user_id, temp_id = 0;

		Connection con = Database.getConn_read();

		String userExists_query = "SELECT user_id FROM user WHERE email=?";

		try {
			PreparedStatement ps = con.prepareStatement(userExists_query);
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();

			if (rs != null) {
				if (rs.next()) {
					temp_id = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (temp_id != 0) {
			user_id = temp_id;
			isRegistered = true;
		}

		return isRegistered;
	}
}
