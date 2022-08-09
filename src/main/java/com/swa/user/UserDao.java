package com.swa.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.swa.user.UserBean;
import com.swa.dbconnection.Database;

public class UserDao {

	public boolean addUser(UserBean user) {
		Connection con = Database.getConn();
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

		Connection con = Database.getConn();
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
		Connection con = Database.getConn();
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

		if (i == 0) {
			return false;
		} else {
			return true;
		}

	}
}
