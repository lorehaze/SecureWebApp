package com.swa.dbconnection;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.DatabaseMetaData;

/*public class Database {

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
}*/

////////////////////////////////

public class Database {

	public static Connection getConn() {
		Connection dbConn = null;
		try {
			// Create Properties object.
			Properties props = new Properties();

			String dbSettingsPropertyFile = "/Users/lorenzo/Desktop/keystore/config.properties";
			// Properties will use a FileReader object as input.
			FileReader fReader = new FileReader(dbSettingsPropertyFile);

			// Load jdbc related properties in above file.
			props.load(fReader);

			// Get each property value.
			String dbDriverClass = props.getProperty("db.driver.class");

			String dbConnUrl = props.getProperty("db.conn.url");

			String dbUserName = props.getProperty("db.username");

			String dbPassword = props.getProperty("db.password");

			if (!"".equals(dbDriverClass) && !"".equals(dbConnUrl)) {
				/* Register jdbc driver class. */
				Class.forName(dbDriverClass);

				// Get database connection object.
				dbConn = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);

				// Get dtabase meta data.
				// DatabaseMetaData dbMetaData = dbConn.getMetaData();

				// Get database name.
				// String dbName = dbMetaData.getDatabaseProductName();

				// Get database version.
				// String dbVersion = dbMetaData.getDatabaseProductVersion();
				//
				// System.out.println("Database Name : " + dbName);
				//
				// System.out.println("Database Version : " + dbVersion);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dbConn;
	}

}