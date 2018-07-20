package com.epam.lab.jdbc.homework;

import java.sql.Connection;

import com.epam.lab.jdbc.homework.dao.*;
import com.epam.lab.jdbc.homework.manage.connection.*;

public class Main {

	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String user = "root";
	private static final String pass = "1234";

	static Connection connection;

	public static void main(String[] args) {
		//Before start you should execute 'university_dump.sql'
		Credentials.setCredentials(driver, URL, user, pass);
		DriverSetup.initDriver(Credentials.DRIVER);
		
		SubjectDAO.getAllSubjects().forEach(System.out::println);
		
		SpecialityDAO.getAllSpecialities().forEach(System.out::println);
		
		GroupDAO.getAllGroups().forEach(System.out::println);
		
		StudentDAO.getAllStudents().forEach(System.out::println);
		
		MarkDAO.getAllMarks().forEach(System.out::println);
	}

}
