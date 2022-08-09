package com.swa.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	public static Connection getConn() {
		String loadDriver = "com.mysql.cj.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:3306/users";
		String dbUSERNAME = "root";
		String dbPASSWORD = "toortoor";

		Connection con = null;
		try {
			Class.forName(loadDriver);
			con = DriverManager.getConnection(dbURL, dbUSERNAME, dbPASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static Connection passConn() {
		String loadDriver = "com.mysql.cj.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:3306/passwords";
		String dbUSERNAME = "root";
		String dbPASSWORD = "toortoor";

		Connection con = null;
		try {
			Class.forName(loadDriver);
			con = DriverManager.getConnection(dbURL, dbUSERNAME, dbPASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
