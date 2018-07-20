package com.epam.lab.jdbc.homework.entities;

public class Subject {
	
	private int subjectCode;
	private String subjectName;
	private String subjectDescription;
	
	public Subject(int subjectCode, String subjectName, String subjectDescription) {
		super();
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.subjectDescription = subjectDescription;
	}

	public int getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectDescription() {
		return subjectDescription;
	}

	public void setSubjectDescription(String subjectDescription) {
		this.subjectDescription = subjectDescription;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Subject ");
		result.append(subjectCode + " ");
		result.append(subjectName + ": ");
		result.append(subjectDescription);
		return result.toString();
	}
}
