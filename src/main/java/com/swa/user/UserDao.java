package com.swa.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import com.swa.dbconnection.Database;
import com.swa.crypt.PasswordHash;

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

	public boolean insertPassword(UserBean user, int userID) throws Exception {
		Connection con = Database.getConn_write();
		PasswordHash pwd = new PasswordHash();
		byte[] notSalted = user.getPassword();
		System.out.println("not salted:" + notSalted);
		byte[] salt = pwd.saltPassword(notSalted, userID);
		System.out.println("Salt UseDao: " + salt);
		String sql = "INSERT INTO password (pass_id,user_id,password) VALUES (NULL,?,?)";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setBytes(2, salt);
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

	public byte[] retrieveSalted(int userID) {
		Connection con = Database.getConn_read();
		String sql = "SELECT password FROM password WHERE user_id=?";
		byte[] hash = null;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();

			if (rs != null) {
				if (rs.next()) {
					hash = rs.getBytes(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hash;
	}

	public boolean login(UserBean user, int userID) {
		int isLogged;

		PasswordHash pwd = new PasswordHash();

		byte[] salt = pwd.getSalt(userID);
		byte[] pass = user.getPassword();
		byte[] temp = pwd.salter(pass, salt);
		pwd.clearArray(salt);
		pwd.clearArray(pass);

		byte[] saltedPwd = retrieveSalted(userID);

		if (Arrays.equals(temp, saltedPwd)) {
			isLogged = 1;
		} else {
			isLogged = 0;
		}

		if (isLogged == 1) {
			return true;
		} else {
			return false;
		}

	}

	public boolean userAlredyRegistered(UserBean user) {
		boolean isRegistered = false;
		int temp_id = 0;

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
			isRegistered = true;
		}

		return isRegistered;
	}
}
