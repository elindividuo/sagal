package com.tpi.sagal.entity;

public class Medicine {
	int id;
	String name;
	Double concentration;
	String unit;


	public Medicine(){
		
	}
	
	
	public Medicine(int id, String name, Double concentration, String unit) {
		super();
		this.id = id;
		this.name = name;
		this.concentration = concentration;
		this.unit = unit;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

	
}
