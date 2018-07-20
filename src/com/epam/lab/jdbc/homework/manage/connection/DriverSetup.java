package com.epam.lab.jdbc.homework.manage.connection;

public class DriverSetup {
	
	public static void initDriver(String driver) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
