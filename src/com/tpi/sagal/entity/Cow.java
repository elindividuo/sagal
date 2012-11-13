package com.tpi.sagal.entity;

import java.util.Date;

public class Cow {
	
	int registration;
	String name;
	String breed;
	Date birthday;
	String tattoo;
	int earNumber;
	Cow father;
	Cow mother;
	String finalDestination;
	int locomotionScoring;
	Hoof hoofFR;
	Hoof hoofFL;
	Hoof hoofBR;
	Hoof hoofBL;
	String[] vaccination;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public int getRegistration() {
		return registration;
	}
	public void setRegistration(int registration) {
		this.registration = registration;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTattoo() {
		return tattoo;
	}
	public void setTatuaje(String tatuaje) {
		this.tattoo = tatuaje;
	}
	public int getEarNumber() {
		return earNumber;
	}
	public void setEarNumber(int earNumber) {
		this.earNumber = earNumber;
	}
	public Cow getFather() {
		return father;
	}
	public void setFather(Cow father) {
		this.father = father;
	}
	public Cow getMother() {
		return mother;
	}
	public void setMother(Cow mother) {
		this.mother = mother;
	}

	public String[] getVaccination() {
		return vaccination;
	}
	public void setVaccination(String[] vaccination) {
		this.vaccination = vaccination;
	}
	public String getFinalDestination() {
		return finalDestination;
	}
	public void setFinalDestination(String finalDestination) {
		this.finalDestination = finalDestination;
	}
	public int getLocomotionScoring() {
		return locomotionScoring;
	}
	public void setLocomotionScoring(int locomotionScoring) {
		this.locomotionScoring = locomotionScoring;
	}
}
