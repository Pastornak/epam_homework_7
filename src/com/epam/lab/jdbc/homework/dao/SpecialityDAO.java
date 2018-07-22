package com.epam.lab.jdbc.homework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.epam.lab.jdbc.homework.manage.connection.ConnectionToDB;
import com.epam.lab.jdbc.homework.entities.Speciality;

public class SpecialityDAO {

	private SpecialityDAO() {
	}

	public static Set<Speciality> getAllSpecialities() {
		Set<Speciality> result = new HashSet<>();
		try (Connection connection = ConnectionToDB.getNewConnection()){
			String query = "SELECT * FROM university.speciality";
			ResultSet rs = connection.createStatement().executeQuery(query);
			Speciality speciality = null;
			while(rs.next()) {
				int specialityCode = rs.getInt(1);
				String specialityName = rs.getString(2);
				String specialityDescription = rs.getString(3);
				speciality = new Speciality(specialityCode, specialityName, specialityDescription);
				result.add(speciality);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	public static Speciality getSpecialityBySpecialityCode(int specialityCode) {
		Speciality result = null;
		try (Connection connection = ConnectionToDB.getNewConnection()){
			String query = "SELECT * FROM university.speciality WHERE speciality_code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, specialityCode);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				String specialityName = rs.getString(2);
				String specialityDescription = rs.getString(3);
				result = new Speciality(specialityCode, specialityName, specialityDescription);
			}
			rs.close();
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	public static void createSpeciality(Speciality speciality) {
		int specialityCode = speciality.getSpecialityCode();
		String specialityName = speciality.getSpecialityName();
		String specialityDescription = speciality.getSpecialityDescription();
		createSpeciality(specialityCode, specialityName, specialityDescription);
	}
	
	public static void createSpeciality(int specialityCode, String specialityName, String specialityDescription) {
		try (Connection connection = ConnectionToDB.getNewConnection()){
			String query = "INSERT INTO university.speciality VALUES(?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, specialityCode);
			statement.setString(2, specialityName);
			statement.setString(3, specialityDescription);
			int rows = statement.executeUpdate();
			if(rows == 0) {
				throw new SQLException("Insert failed");
			}
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void updateSpeciality(Speciality newSpeciality) {
		int oldSpecialityCode = newSpeciality.getSpecialityCode();
		String name = newSpeciality.getSpecialityName();
		String description = newSpeciality.getSpecialityDescription();
		updateSpeciality(oldSpecialityCode, name, description);
	}
	
	public static void updateSpeciality(int oldSpecialityCode, String newSpecialityName, String newSpecialityDescription) {
		try (Connection connection = ConnectionToDB.getNewConnection()){
			String query = "UPDATE university.speciality SET speciality_name = ?, speciality_description = ? WHERE speciality_code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newSpecialityName);
			statement.setString(2, newSpecialityDescription);
			statement.setInt(3, oldSpecialityCode);
			int rows = statement.executeUpdate();
			if(rows == 0) {
				throw new SQLException("Update failed");
			}
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void deleteSpeciality(Speciality speciality) {
		int specialityCode = speciality.getSpecialityCode();
		deleteSpeciality(specialityCode);
	}
	
	public static void deleteSpeciality(int specialityCode) {
		try (Connection connection = ConnectionToDB.getNewConnection()){
			String query = "DELETE FROM university.speciality WHERE speciality_code = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, specialityCode);
			int rows = statement.executeUpdate();
			System.out.println("Deleted " + rows + " rows");
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static boolean isEquals(Speciality speciality) {
		Speciality specialityFromDB = getSpecialityBySpecialityCode(speciality.getSpecialityCode());
		return (specialityFromDB != null
					&& specialityFromDB.getSpecialityName().equals(speciality.getSpecialityName())
					&& specialityFromDB.getSpecialityDescription().equals(speciality.getSpecialityDescription()));
	}
}
