package com.epam.lab.jdbc.homework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.epam.lab.jdbc.homework.manage.connection.ConnectionToDB;
import com.epam.lab.jdbc.homework.entities.Subject;

public class SubjectDAO {

	private SubjectDAO() {
	}

	public static Set<Subject> getAllSubjects() {
		Set<Subject> result = new HashSet<>();
		try (Connection connection = ConnectionToDB.getNewConnection()){
			String query = "SELECT * FROM university.subject";
			ResultSet rs = connection.createStatement().executeQuery(query);
			Subject subject = null;
			while(rs.next()) {
				int subjectCode = rs.getInt(1);
				String subjectName = rs.getString(2);
				String subjectDescription = rs.getString(3);
				subject = new Subject(subjectCode, subjectName, subjectDescription);
				result.add(subject);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	public static Subject getSubjectBySubjectCode(int subjectCode) {
		Subject result = null;
		try (Connection connection = ConnectionToDB.getNewConnection()){
			String query = "SELECT * FROM university.subject WHERE subject_code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, subjectCode);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				String subjectName = rs.getString(2);
				String subjectDescription = rs.getString(3);
				result = new Subject(subjectCode, subjectName, subjectDescription);
			}
			rs.close();
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	public static void createSubject(Subject subject) {
		int subjectCode = subject.getSubjectCode();
		String subjectName = subject.getSubjectName();
		String subjectDescription = subject.getSubjectDescription();
		createSubject(subjectCode, subjectName, subjectDescription);
	}
	
	public static void createSubject(int subjectCode, String subjectName, String subjectDescription) {
		try (Connection connection = ConnectionToDB.getNewConnection()){
			String query = "INSERT INTO university.subject VALUES(?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, subjectCode);
			statement.setString(2, subjectName);
			statement.setString(3, subjectDescription);
			int rows = statement.executeUpdate();
			if(rows == 0) {
				throw new SQLException("Insert failed");
			}
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void updateSubject(Subject newSubject) {
		int oldSubjectCode = newSubject.getSubjectCode();
		String name = newSubject.getSubjectName();
		String description = newSubject.getSubjectDescription();
		updateSubject(oldSubjectCode, name, description);
	}
	
	public static void updateSubject(int oldSubjectCode, String newSubjectName, String newSubjectDescription) {
		try (Connection connection = ConnectionToDB.getNewConnection()){
			String query = "UPDATE university.subject SET subject_name = ?, subject_description = ? WHERE subject_code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newSubjectName);
			statement.setString(2, newSubjectDescription);
			statement.setInt(3, oldSubjectCode);
			int rows = statement.executeUpdate();
			if(rows == 0) {
				throw new SQLException("Update failed");
			}
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void deleteSubject(Subject subject) {
		int subjectCode = subject.getSubjectCode();
		deleteSubject(subjectCode);
	}
	
	public static void deleteSubject(int subjectCode) {
		try (Connection connection = ConnectionToDB.getNewConnection()){
			String query = "DELETE FROM university.subject WHERE subject_code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, subjectCode);
			int rows = statement.executeUpdate();
			System.out.println("Deleted " + rows + " rows");
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static boolean isEquals(Subject subject) {
		Subject subjectFromDB = getSubjectBySubjectCode(subject.getSubjectCode());
		return (subjectFromDB != null 
				&& subjectFromDB.getSubjectName().equals(subject.getSubjectName())
				&& subjectFromDB.getSubjectDescription().equals(subject.getSubjectDescription()));
	}
}
