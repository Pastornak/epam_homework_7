package com.epam.lab.jdbc.homework.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.epam.lab.jdbc.homework.manage.connection.ConnectionToDB;
import com.epam.lab.jdbc.homework.entities.Mark;
import com.epam.lab.jdbc.homework.entities.Student;
import com.epam.lab.jdbc.homework.entities.Subject;

public class MarkDAO {

	public static Set<Mark> getAllMarks() {
		Set<Mark> result = new HashSet<>();
		Connection connection = null;
		try {
			String query = "SELECT * FROM university.mark_for_exam";
			connection = ConnectionToDB.getNewConnection();
			ResultSet rs = connection.createStatement().executeQuery(query);
			Mark mark = null;
			while(rs.next()) {
				int studentCode = rs.getInt(1);
				Student student = StudentDAO.getStudentByStudentCode(studentCode);
				int subjectCode = rs.getInt(2);
				Subject subject = SubjectDAO.getSubjectBySubjectCode(subjectCode);
				Date examindationDate = rs.getDate(3);
				int markScore = rs.getInt(4);
				mark = new Mark(student, subject, examindationDate, markScore);
				result.add(mark);
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
	
	public static Mark getMarkByStudentSubject(Student student, Subject subject) {
		int studentCode = student.getStudentCode();
		int subjectCode = subject.getSubjectCode();
		return getMarkByStudentSubject(studentCode, subjectCode);
	}
	
	public static Mark getMarkByStudentSubject(int studentCode, int subjectCode) {
		Mark result = null;
		Connection connection = null;
		try {
			String query = "SELECT * FROM university.mark_for_exam WHERE student_code = ? AND subject_code = ?";
			connection = ConnectionToDB.getNewConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, studentCode);
			statement.setInt(2, subjectCode);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				Student student = StudentDAO.getStudentByStudentCode(studentCode);
				Subject subject = SubjectDAO.getSubjectBySubjectCode(subjectCode);
				Date examindationDate = rs.getDate(3);
				int markScore = rs.getInt(4);
				result = new Mark(student, subject, examindationDate, markScore);
			}
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
	
	public static void createMark(Mark mark) {
		Student student = mark.getStudent();
		Subject subject = mark.getSubject();
		Date date = mark.getExaminationDate();
		int markScore = mark.getMark();
		createMark(student, subject, date, markScore);
	}
	
	public static void createMark(Student student, Subject subject, Date date, int markScore) {
		int studentCode = student.getStudentCode();
		int subjectCode = subject.getSubjectCode();
		createMark(studentCode, subjectCode, date, markScore);
	}
	
	public static void createMark(int studentCode, int subjectCode, Date date, int markScore) {
		Connection connection = null;
		try {
			String query = "INSERT INTO university.mark_for_exam VALUES(?, ?, ?, ?)";
			connection = ConnectionToDB.getNewConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, studentCode);
			statement.setInt(2, subjectCode);
			statement.setDate(3, date);
			statement.setInt(4, markScore);
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
	
	public static void updateMark(Mark mark) {
		Student student = mark.getStudent();
		Subject subject = mark.getSubject();
		Date date = mark.getExaminationDate();
		int markScore = mark.getMark();
		updateMark(student, subject, date, markScore);
	}
	
	public static void updateMark(Student student, Subject subject, Date date, int markScore) {
		int studentCode = student.getStudentCode();
		int subjectCode = subject.getSubjectCode();
		updateMark(studentCode, subjectCode, date, markScore);
	}
	
	public static void updateMark(int studentCode, int subjectCode, Date date, int markScore) {
		Connection connection = null;
		try {
			String query = "UPDATE university.mark_for_exam SET date_of_examination = ?, mark = ? WHERE student_code = ? AND subject_code = ?";
			connection = ConnectionToDB.getNewConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDate(1, date);
			statement.setInt(2, markScore);
			statement.setInt(3, studentCode);
			statement.setInt(4, subjectCode);
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
	
	public static void deleteMark(Mark mark) {
		Student student = mark.getStudent();
		Subject subject = mark.getSubject();
		deleteMark(student, subject);
	}
	
	public static void deleteMark(Student student, Subject subject) {
		int studentCode = student.getStudentCode();
		int subjectCode = subject.getSubjectCode();
		deleteMark(studentCode, subjectCode);
	}
	
	public static void deleteMark(int studentCode, int subjectCode) {
		Connection connection = null;
		try {
			String query = "DELETE FROM university.mark_for_exam WHERE student_code = ? AND subject_code = ?";
			connection = ConnectionToDB.getNewConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, studentCode);
			statement.setInt(2, subjectCode);
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
