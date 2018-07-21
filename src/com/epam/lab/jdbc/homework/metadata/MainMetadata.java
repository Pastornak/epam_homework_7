package com.epam.lab.jdbc.homework.metadata;

import com.epam.lab.jdbc.homework.manage.connection.Credentials;
import com.epam.lab.jdbc.homework.manage.connection.DriverSetup;

public class MainMetadata {

	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String user = "root";
	private static final String pass = "1234";
	
	public static void main(String[] args) {
		Credentials.setCredentials(driver, URL, user, pass);
		DriverSetup.initDriver(Credentials.DRIVER);
		MetadataReader.startUI();
	}

}
