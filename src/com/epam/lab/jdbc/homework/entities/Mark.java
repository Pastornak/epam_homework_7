package com.epam.lab.jdbc.homework.entities;

import java.sql.Date;

public class Mark {

	private Student student;
	private Subject subject;
	private Date examinationDate;
	private int mark;
	
	public Mark() {}
	
	public Mark(Student student, Subject subject, Date examinationDate, int mark) {
		super();
		this.student = student;
		this.subject = subject;
		this.examinationDate = examinationDate;
		this.mark = mark;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Date getExaminationDate() {
		return examinationDate;
	}

	public void setExaminationDate(Date examinationDate) {
		this.examinationDate = examinationDate;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder("Mark of ");
		result.append(student.getStudentCode() + " ");
		result.append(student.getSurname() + " ");
		result.append(student.getName() + " ");
		result.append(student.getPatronymic() + " ");
		result.append("for ");
		result.append(subject.getSubjectCode() + " ");
		result.append(subject.getSubjectName() + " ");
		result.append("is ");
		result.append(mark + " ");
		result.append("due ");
		result.append(examinationDate);
		return result.toString();
	}
}
