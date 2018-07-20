package com.epam.lab.jdbc.homework.transactions;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.epam.lab.jdbc.homework.manage.connection.ConnectionToDB;

public class SpecialityTransaction {

	public static void specialitySuccessfulExampleTransaction() {
		System.out.println("Started speciality transaction");
		Connection connection = null;
		try {
			connection = ConnectionToDB.getNewConnection();
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			String insertQuery = "INSERT INTO university.speciality VALUES(911, 'Test speciality', 'Test speciality')";
			String updateQuery = "UPDATE university.speciality SET speciality_name = 'Updated test speciality' WHERE speciality_code = 911";
			String deleteQuery = "DELETE FROM university.speciality WHERE speciality_code = 911";
			statement.executeUpdate(insertQuery);
			statement.executeUpdate(updateQuery);
			statement.executeUpdate(deleteQuery);
			connection.commit();
			statement.close();
			System.out.println("Finished speciality transaction successful");
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("Finished speciality transaction failed");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
