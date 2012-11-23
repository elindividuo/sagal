package com.tpi.sagal.entity;

public class Vaccine {
	int id;
	String name;
	
	public Vaccine(){
		
	}
	
	public Vaccine(int id, String vaccine) {
		this.id = id;
		this.name = vaccine;
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
	
	
}
