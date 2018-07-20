package com.epam.lab.jdbc.homework.manage.connection;

public class Credentials {

	static {
		DRIVER = "";
		URL = "";
		USERNAME = "";
		PASSWORD = "";
	}
	
	public static String DRIVER;
	public static String URL;
	public static String USERNAME;
	public static String PASSWORD;
	
	private Credentials() {}
	
	public static void setCredentials(String driver, String url, String username, String password) {
		DRIVER = driver;
		URL = url;
		USERNAME = username;
		PASSWORD = password;
	}
}
