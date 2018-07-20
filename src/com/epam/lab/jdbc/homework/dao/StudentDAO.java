package com.epam.lab.jdbc.homework.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.epam.lab.jdbc.homework.manage.connection.ConnectionToDB;
import com.epam.lab.jdbc.homework.entities.Group;
import com.epam.lab.jdbc.homework.entities.Speciality;
import com.epam.lab.jdbc.homework.entities.Student;

public class StudentDAO {

	private StudentDAO() {}
	
	public static Set<Student> getAllStudents() {
		Set<Student> result = new HashSet<>();
		Connection connection = null;
		try {
			String query = "SELECT * FROM university.student";
			connection = ConnectionToDB.getNewConnection();
			ResultSet rs = connection.createStatement().executeQuery(query);
			Student student = null;
			while(rs.next()) {
				int studentCode = rs.getInt(1);
				String surname = rs.getString(2);
				String name = rs.getString(3);
				String patronymic = rs.getString(4);
				String sex = rs.getString(5);
				Date dateOfBirth = rs.getDate(6);
				String address = rs.getString(7);
				String phoneNumber = rs.getString(8);
				String passportInfo = rs.getString(9);
				String gradebookNumber = rs.getString(10);
				Date entryDate = rs.getDate(11);
				Group group = GroupDAO.getGroupByGroupId(rs.getInt(12));
				Speciality speciality = SpecialityDAO.getSpecialityBySpecialityCode(rs.getInt(13));
				String formOfStudying = rs.getString(14);
				String motherName = rs.getString(15);
				String fatherName = rs.getString(16);
				student = new Student(studentCode, surname, name, patronymic, sex, dateOfBirth,
						address, phoneNumber, passportInfo, gradebookNumber, entryDate,
						group, speciality, formOfStudying, motherName, fatherName);
				result.add(student);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static Student getStudentByStudentCode(int studentCode) {
		Student result = null;
		Connection connection = null;
		try {
			String query = "SELECT * FROM university.student WHERE student_code = ?";
			connection = ConnectionToDB.getNewConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, studentCode);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				String surname = rs.getString(2);
				String name = rs.getString(3);
				String patronymic = rs.getString(4);
				String sex = rs.getString(5);
				Date dateOfBirth = rs.getDate(6);
				String address = rs.getString(7);
				String phoneNumber = rs.getString(8);
				String passportInfo = rs.getString(9);
				String gradebookNumber = rs.getString(10);
				Date entryDate = rs.getDate(11);
				Group group = GroupDAO.getGroupByGroupId(rs.getInt(12));
				Speciality speciality = SpecialityDAO.getSpecialityBySpecialityCode(rs.getInt(13));
				String formOfStudying = rs.getString(14);
				String motherName = rs.getString(15);
				String fatherName = rs.getString(16);
				result = new Student(studentCode, surname, name, patronymic, sex, dateOfBirth,
						address, phoneNumber, passportInfo, gradebookNumber, entryDate,
						group, speciality, formOfStudying, motherName, fatherName);
			}
			rs.close();
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static void createStudent(Student student) {
		int studentCode = student.getStudentCode();
		String surname = student.getSurname();
		String name = student.getName();
		String patronymic = student.getPatronymic();
		String sex = student.getSex();
		Date dateOfBirth = student.getDateOfBirth();
		String address = student.getAddress();
		String phoneNumber = student.getPhoneNumber();
		String passportInfo = student.getPassportInfo();
		String gradebookNumber = student.getGradebookNumber();
		Date entryDate = student.getEntryDate();
		Group group = student.getGroup();
		Speciality speciality = student.getSpeciality();
		String formOfStudying = student.getFormOfStudying();
		String motherName = student.getMotherName();
		String fatherName = student.getFatherName();
		createStudent(studentCode, surname, name, patronymic, sex, dateOfBirth,
				address, phoneNumber, passportInfo, gradebookNumber, entryDate,
				group, speciality, formOfStudying, motherName, fatherName);
	}
	
	public static void createStudent(int studentCode, String surname, String name, String patronymic, String sex, Date dateOfBirth,
			String address, String phoneNumber, String passportInfo, String gradebookNumber, Date entryDate,
			Group group, Speciality speciality, String formOfStudying, String motherName, String fatherName) {
		int groupId = group.getGroupId();
		int specialityCode = speciality.getSpecialityCode();
		createStudent(studentCode, surname, name, patronymic, sex, dateOfBirth,
				address, phoneNumber, passportInfo, gradebookNumber, entryDate,
				groupId, specialityCode, formOfStudying, motherName, fatherName);
	}
	
	public static void createStudent(int studentCode, String surname, String name, String patronymic, String sex, Date dateOfBirth,
			String address, String phoneNumber, String passportInfo, String gradebookNumber, Date entryDate,
			int groupId, int specialityCode, String formOfStudying, String motherName, String fatherName) {
		Connection connection = null;
		try {
			String query = "INSERT INTO university.student VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			connection = ConnectionToDB.getNewConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, studentCode);
			statement.setString(2, surname);
			statement.setString(3, name);
			statement.setString(4, patronymic);
			statement.setString(5, sex);
			statement.setDate(6, dateOfBirth);
			statement.setString(7, address);
			statement.setString(8, phoneNumber);
			statement.setString(9, passportInfo);
			statement.setString(10, gradebookNumber);
			statement.setDate(11, entryDate);
			statement.setInt(12, groupId);
			statement.setInt(13, specialityCode);
			statement.setString(14, formOfStudying);
			statement.setString(15, motherName);
			statement.setString(16, fatherName);
			int rows = statement.executeUpdate();
			if(rows == 0) {
				throw new SQLException("Insert failed");
			}
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void updateStudent(Student student) {
		int studentCode = student.getStudentCode();
		String surname = student.getSurname();
		String name = student.getName();
		String patronymic = student.getPatronymic();
		String sex = student.getSex();
		Date dateOfBirth = student.getDateOfBirth();
		String address = student.getAddress();
		String phoneNumber = student.getPhoneNumber();
		String passportInfo = student.getPassportInfo();
		String gradebookNumber = student.getGradebookNumber();
		Date entryDate = student.getEntryDate();
		Group group = student.getGroup();
		Speciality speciality = student.getSpeciality();
		String formOfStudying = student.getFormOfStudying();
		String motherName = student.getMotherName();
		String fatherName = student.getFatherName();
		updateStudent(studentCode, surname, name, patronymic, sex, dateOfBirth,
				address, phoneNumber, passportInfo, gradebookNumber, entryDate,
				group, speciality, formOfStudying, motherName, fatherName);
	}
	
	public static void updateStudent(int studentCode, String surname, String name, String patronymic, String sex, Date dateOfBirth,
			String address, String phoneNumber, String passportInfo, String gradebookNumber, Date entryDate,
			Group group, Speciality speciality, String formOfStudying, String motherName, String fatherName) {
		int groupId = group.getGroupId();
		int specialityCode = speciality.getSpecialityCode();
		updateStudent(studentCode, surname, name, patronymic, sex, dateOfBirth,
				address, phoneNumber, passportInfo, gradebookNumber, entryDate,
				groupId, specialityCode, formOfStudying, motherName, fatherName);
	}
	
	public static void updateStudent(int studentCode, String surname, String name, String patronymic, String sex, Date dateOfBirth,
			String address, String phoneNumber, String passportInfo, String gradebookNumber, Date entryDate,
			int groupId, int specialityCode, String formOfStudying, String motherName, String fatherName) {
		Connection connection = null;
		try {
			String query = "UPDATE university.student SET surname = ?, name = ?, patronymic = ?, sex = ?, date_of_birth = ?,"
					+ "address = ?, phone = ?, passport_info = ?, gradebook_number = ?, entry_date = ?, group_id = ?,"
					+ "speciality_code = ?, form_of_studying = ?, name_of_mother = ?, name_of_father = ? WHERE student_code + ?";
			connection = ConnectionToDB.getNewConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, surname);
			statement.setString(2, name);
			statement.setString(3, patronymic);
			statement.setString(4, sex);
			statement.setDate(5, dateOfBirth);
			statement.setString(6, address);
			statement.setString(7, phoneNumber);
			statement.setString(8, passportInfo);
			statement.setString(9, gradebookNumber);
			statement.setDate(10, entryDate);
			statement.setInt(11, groupId);
			statement.setInt(12, specialityCode);
			statement.setString(13, formOfStudying);
			statement.setString(14, motherName);
			statement.setString(15, fatherName);
			statement.setInt(16, studentCode);
			int rows = statement.executeUpdate();
			if(rows == 0) {
				throw new SQLException("Update failed");
			}
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void deleteStudent(Student student) {
		int studentCode = student.getStudentCode();
		deleteStudent(studentCode);
	}
	
	public static void deleteStudent(int studentCode) {
		Connection connection = null;
		try {
			String query = "DELETE FROM university.student WHERE student_code = ?";
			connection = ConnectionToDB.getNewConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, studentCode);
			int rows = statement.executeUpdate();
			System.out.println("Deleted " + rows + " rows");
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
