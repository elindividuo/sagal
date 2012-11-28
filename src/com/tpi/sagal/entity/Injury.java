package com.tpi.sagal.entity;

public class Injury {

	int id;
	String name;
	int infectious;
	

	public Injury(){
		
	}
	
	public Injury(int id, String name, int infectious) {
		this.id = id;
		this.name = name;
		this.infectious=infectious;
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
	public int getInfectious() {
		return infectious;
	}

	public void setInfectious(int infectious) {
		this.infectious = infectious;
	}

}
