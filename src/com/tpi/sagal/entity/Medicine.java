package com.tpi.sagal.entity;

public class Medicine {
	int id;
	String name;
	Double concentration;
	
	
	public Medicine(){
		
	}
	
	
	public Medicine(int id, String name, Double concentration) {
		super();
		this.id = id;
		this.name = name;
		this.concentration = concentration;
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
	public Double getConcentration() {
		return concentration;
	}
	public void setConcentration(Double concentration) {
		this.concentration = concentration;
	}
	
}
