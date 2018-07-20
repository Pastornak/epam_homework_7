package com.epam.lab.jdbc.homework.entities;

public class Group {

	private int groupId;
	private String groupName;
	private int yearOfStudying;
	private Speciality speciality;
	
	public Group(int groupId, String groupName, int yearOfStudying, Speciality speciality) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.yearOfStudying = yearOfStudying;
		this.speciality = speciality;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getYearOfStudying() {
		return yearOfStudying;
	}

	public void setYearOfStudying(int yearOfStudying) {
		this.yearOfStudying = yearOfStudying;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder("Group ");
		result.append(groupId + " ");
		result.append(groupName + " ");
		result.append(yearOfStudying + " ");
		result.append(speciality.toString());
		return result.toString();
	}
}
