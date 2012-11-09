package com.tpi.sagal.entity;

public class Farm {
	
	int id;
	String name;
	String address;
	String ownerName;
	
	public Farm (int id, String name, String address, String ownerName ){
		this.id=id;
		this.name=name;
		this.address=address;
		this.ownerName=ownerName;
	}
	
	public Farm(){
		
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getAdress() {
		return address;
	}
	public void setAdress(String adress) {
		this.address = address;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
