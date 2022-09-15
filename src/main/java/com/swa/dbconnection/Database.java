package com.swa.dbconnection;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Database {

	public static Connection getConn_read() {
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

			String dbUserName = props.getProperty("db.read");

			String dbPassword = props.getProperty("db.read.pwd");

			if (!"".equals(dbDriverClass) && !"".equals(dbConnUrl)) {
				/* Register jdbc driver class. */
				Class.forName(dbDriverClass);

				// Get database connection object.
				dbConn = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dbConn;
	}
	
	public static Connection getConn_write() {
		Connection dbConn = null;
		try {
			// Create Properties object.
			Properties props = new Properties();

			// Properties will use a FileReader object as input.
			String dbSettingsPropertyFile = "/Users/lorenzo/Desktop/keystore/config.properties";
			FileReader fReader = new FileReader(dbSettingsPropertyFile);

			// Load jdbc related properties in above file.
			props.load(fReader);

			// Get each property value.
			String dbDriverClass = props.getProperty("db.driver.class");

			String dbConnUrl = props.getProperty("db.conn.url");

			String dbUserName = props.getProperty("db.write");

			String dbPassword = props.getProperty("db.write.pwd");

			if (!"".equals(dbDriverClass) && !"".equals(dbConnUrl)) {
				/* Register jdbc driver class. */
				Class.forName(dbDriverClass);

				// Get database connection object.
				dbConn = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dbConn;
	}
	
	public static Connection getConn_update() {
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

			String dbUserName = props.getProperty("db.update");

			String dbPassword = props.getProperty("db.update.pwd");

			if (!"".equals(dbDriverClass) && !"".equals(dbConnUrl)) {
				/* Register jdbc driver class. */
				Class.forName(dbDriverClass);

				// Get database connection object.
				dbConn = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dbConn;
	}

}