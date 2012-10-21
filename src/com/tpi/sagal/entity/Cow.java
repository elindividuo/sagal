package com.tpi.sagal.entity;

import java.util.Date;

public class Cow {

	String name;
	String breed;
	String registration;
	Date birthday;
	String tatuaje;
	int earNumber;
	Cow father;
	Cow mother;
	Cow[] grandparentsPaternal;
	Cow[] grandparentsMaternal;
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
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTatuaje() {
		return tatuaje;
	}
	public void setTatuaje(String tatuaje) {
		this.tatuaje = tatuaje;
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
	public Cow[] getGrandparentsPaternal() {
		return grandparentsPaternal;
	}
	public void setGrandparentsPaternal(Cow[] grandparentsPaternal) {
		this.grandparentsPaternal = grandparentsPaternal;
	}
	public Cow[] getGrandparentsMaternal() {
		return grandparentsMaternal;
	}
	public void setGrandparentsMaternal(Cow[] grandparentsMaternal) {
		this.grandparentsMaternal = grandparentsMaternal;
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
	String finalDestination;
	int locomotionScoring;

}
