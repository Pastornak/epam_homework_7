package com.epam.lab.jdbc.homework.entities;

import java.sql.Date;

public class Student {

	private int studentCode;
	private String surname;
	private String name;
	private String patronymic;
	private String sex;
	private Date dateOfBirth;
	private String address;
	private String phoneNumber;
	private String passportInfo;
	private String gradebookNumber;
	private Date entryDate;
	private Group group;
	private Speciality speciality;
	private String formOfStudying;
	private String motherName;
	private String fatherName;
	
	public Student(int studentCode, String surname, String name, String patronymic, String sex, Date dateOfBirth,
			String address, String phoneNumber, String passportInfo, String gradebookNumber, Date entryDate,
			Group group, Speciality speciality, String formOfStudying, String motherName, String fatherName) {
		super();
		this.studentCode = studentCode;
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
		this.sex = sex;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.passportInfo = passportInfo;
		this.gradebookNumber = gradebookNumber;
		this.entryDate = entryDate;
		this.group = group;
		this.speciality = speciality;
		this.formOfStudying = formOfStudying;
		this.motherName = motherName;
		this.fatherName = fatherName;
	}

	public int getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(int studentCode) {
		this.studentCode = studentCode;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassportInfo() {
		return passportInfo;
	}

	public void setPassportInfo(String passportInfo) {
		this.passportInfo = passportInfo;
	}

	public String getGradebookNumber() {
		return gradebookNumber;
	}

	public void setGradebookNumber(String gradebookNumber) {
		this.gradebookNumber = gradebookNumber;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public String getFormOfStudying() {
		return formOfStudying;
	}

	public void setFormOfStudying(String formOfStudying) {
		this.formOfStudying = formOfStudying;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder("Student ");
		result.append(studentCode + " ");
		result.append(surname + " ");
		result.append(name + " ");
		result.append(patronymic + " ");
		result.append(sex + " ");
		result.append(dateOfBirth + " ");
		result.append(address + " ");
		result.append(phoneNumber + " ");
		result.append(passportInfo + " ");
		result.append(gradebookNumber + " ");
		result.append(entryDate + " ");
		result.append(group + " ");
		result.append(speciality + " ");
		result.append(formOfStudying + " ");
		result.append(motherName + " ");
		result.append(fatherName);
		return result.toString();
	}
}
