package com.tpi.sagal.entity;

import java.util.Date;

public class Cow {

	int id;
	String name;
	String breed;
	Date birthday;
	String tattoo;
	Cow father;
	Cow mother;
	String finalDestination;
	String problems;
	int locomotionScoring;
	String[] vaccination;
	Farm farm;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProblems() {
		return problems;
	}
	public void setProblems(String problems) {
		this.problems = problems;
	}
	public Farm getFarm() {
		return farm;
	}
	public void setFarm(Farm farm) {
		this.farm = farm;
	}
	public void setTattoo(String tattoo) {
		this.tattoo = tattoo;
	}
	
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
