package com.tpi.sagal.entity;

public class Injury {

	int id;
	String name;
	String abbreviation;
	
	public Injury(){
		
	}
	
	public Injury(int id, String name, String abbreviation) {
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
}
