package com.epam.lab.jdbc.homework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.epam.lab.jdbc.homework.manage.connection.ConnectionToDB;
import com.epam.lab.jdbc.homework.entities.Group;
import com.epam.lab.jdbc.homework.entities.Speciality;

public class GroupDAO {

	private GroupDAO() {}
	
	public static Set<Group> getAllGroups(){
		Set<Group> result = new HashSet<>();
		try (Connection connection = ConnectionToDB.getNewConnection()){
			String query = "SELECT * FROM university.group";
			ResultSet rs = connection.createStatement().executeQuery(query);
			Group group = null;
			while(rs.next()) {
				int groupId = rs.getInt(1);
				String groupName = rs.getString(2);
				int yearOfStudying = rs.getInt(3);
				Speciality speciality = SpecialityDAO.getSpecialityBySpecialityCode(rs.getInt(4));
				group = new Group(groupId, groupName, yearOfStudying, speciality);
				result.add(group);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	public static Group getGroupByGroupId(int inputGroupId) {
		Group result = null;
		try (Connection connection = ConnectionToDB.getNewConnection()){
			String query = "SELECT * FROM university.group WHERE group_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, inputGroupId);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				int groupId = rs.getInt(1);
				String groupName = rs.getString(2);
				int yearOfStudying = rs.getInt(3);
				Speciality speciality = SpecialityDAO.getSpecialityBySpecialityCode(rs.getInt(4));
				result = new Group(groupId, groupName, yearOfStudying, speciality);
			}
			rs.close();
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void createGroup(Group group) {
		int groupId = group.getGroupId();
		String groupName = group.getGroupName();
		int yearOfStudying = group.getYearOfStudying();
		Speciality speciality = group.getSpeciality();
		createGroup(groupId, groupName, yearOfStudying, speciality);
	}
	
	public static void createGroup(int groupId, String groupName, int yearOfStudying, Speciality speciality) {
		int specialityCode = speciality.getSpecialityCode();
		createGroup(groupId, groupName, yearOfStudying, specialityCode);
	}
	
	public static void createGroup(int groupId, String groupName, int yearOfStudying, int specialityCode) {
		try (Connection connection = ConnectionToDB.getNewConnection()){
			String query = "INSERT INTO university.group VALUES(?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, groupId);
			statement.setString(2, groupName);
			statement.setInt(3, yearOfStudying);
			statement.setInt(4, specialityCode);
			int rows = statement.executeUpdate();
			if(rows == 0) {
				throw new SQLException("Insert failed");
			}
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void updateGroup(Group group) {
		int groupId = group.getGroupId();
		String groupName = group.getGroupName();
		int yearOfStudying = group.getYearOfStudying();
		Speciality speciality = group.getSpeciality();
		updateGroup(groupId, groupName, yearOfStudying, speciality);
	}
	
	public static void updateGroup(int groupId, String groupName, int yearOfStudying, Speciality speciality) {
		int specialityCode = speciality.getSpecialityCode();
		updateGroup(groupId, groupName, yearOfStudying, specialityCode);
	}
	
	public static void updateGroup(int groupId, String groupName, int yearOfStudying, int specialityCode) {
		try (Connection connection = ConnectionToDB.getNewConnection()){
			String query = "UPDATE university.group SET group_name = ?, year_of_studying = ?, speciality_code = ? WHERE group_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, groupName);
			statement.setInt(2, yearOfStudying);
			statement.setInt(3, specialityCode);
			statement.setInt(4,  groupId);
			int rows = statement.executeUpdate();
			if(rows == 0) {
				throw new SQLException("Update failed");
			}
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void deleteGroup(Group group) {
		int groupId = group.getGroupId();
		deleteGroup(groupId);
	}
	
	//Made as 1 transaction
	public static void deleteGroup(int groupId) {
		Connection connection = null;
		try {
			connection = ConnectionToDB.getNewConnection();
			connection.setAutoCommit(false);
			Group groupToDelete = GroupDAO.getGroupByGroupId(groupId);
			int yearOfStudying = groupToDelete.getYearOfStudying();
			int specialityCode = groupToDelete.getSpeciality().getSpecialityCode();
			Integer newGroupId = null;
			String alternativeQuery = "SELECT * FROM university.`group` WHERE group_id != ? AND year_of_studying = ? AND speciality_code = ?";
			PreparedStatement alternativeGroupStatement = connection.prepareStatement(alternativeQuery);
			alternativeGroupStatement.setInt(1, groupId);
			alternativeGroupStatement.setInt(2, yearOfStudying);
			alternativeGroupStatement.setInt(3, specialityCode);
			//System.out.println(alternativeGroupStatement.)
			ResultSet alternativeGroupRS = alternativeGroupStatement.executeQuery();
			if(alternativeGroupRS.next()) {
				newGroupId = alternativeGroupRS.getInt(1);
			} else {
				throw new SQLException("No alternative groups found, can't delete");
			}
			alternativeGroupRS.close();
			alternativeGroupStatement.close();
			String studentsToTransferQuery = "UPDATE university.student SET group_id = ? WHERE group_id = ?";
			PreparedStatement transferStatement = connection.prepareStatement(studentsToTransferQuery);
			transferStatement.setInt(1, newGroupId);
			transferStatement.setInt(2, groupId);
			int transferedStudents = transferStatement.executeUpdate();
			System.out.println("Transfered " + transferedStudents + " students to other group.");
			transferStatement.close();
			String deleteQuery = "DELETE FROM university.group WHERE group_id = ?";
			PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, groupId);
			int rows = deleteStatement.executeUpdate();
			System.out.println("Deleted " + rows + " rows");
			deleteStatement.close();
			connection.commit();
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
