package com.tpi.sagal.entity;

public class Footbath {

	int id;
	String name;
	double width;
	double deep;
	double height;
	double medicineQuantity;
	Farm farm;
	Medicine medicine;
	
	public Footbath(){
		
	}
	
	public Footbath(int id, String name, double width, double deep, double height, double quantity, Farm farm, Medicine medicine) {
		this.id = id;
		this.name = name;
		this.width = width;
		this.deep = deep;
		this.height = height;
		this.medicineQuantity = quantity;
		this.farm= farm;
		this.medicine = medicine;
	}
	public double getMedicineQuantity() {
		return medicineQuantity;
	}

	public void setMedicineQuantity(double medicineQuantity) {
		this.medicineQuantity = medicineQuantity;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Farm getFarm() {
		return farm;
	}
	public void setFarm(Farm farm) {
		this.farm = farm;
	}
	public double getQuantity() {
		return medicineQuantity;
	}
	public void setQuantity(double quantity) {
		this.medicineQuantity = quantity;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getDeep() {
		return deep;
	}
	public void setDeep(double deep) {
		this.deep = deep;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
