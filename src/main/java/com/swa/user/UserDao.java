package com.swa.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
}
