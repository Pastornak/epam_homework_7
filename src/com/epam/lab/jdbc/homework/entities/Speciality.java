package com.epam.lab.jdbc.homework.entities;

public class Speciality {

	private int specialityCode;
	private String specialityName;
	private String specialityDescription;
	
	public Speciality() {}
	
	public Speciality(int specialityCode, String specialityName, String specialityDescription) {
		super();
		this.specialityCode = specialityCode;
		this.specialityName = specialityName;
		this.specialityDescription = specialityDescription;
	}

	public int getSpecialityCode() {
		return specialityCode;
	}

	public void setSpecialityCode(int specialityCode) {
		this.specialityCode = specialityCode;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getSpecialityDescription() {
		return specialityDescription;
	}

	public void setSpecialityDescription(String specialityDescription) {
		this.specialityDescription = specialityDescription;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Speciality ");
		result.append(specialityCode + " ");
		result.append("\"" + specialityName + "\": ");
		result.append(specialityDescription);
		return result.toString();
	}
}
