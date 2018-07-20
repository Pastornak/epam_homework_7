package com.epam.lab.jdbc.homework.manage.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {
	
	private ConnectionToDB() {}

	public static Connection getNewConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(Credentials.URL, Credentials.USERNAME, Credentials.PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
