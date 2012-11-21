package com.tpi.sagal.entity;

public class Cow {

	int id;
	int registry;
	String name;
	String breed;
	String birthday;
	String tattoo;
	Cow father;
	Cow mother;
	String finalDestination;
	String problems;
	int locomotionScoring;
	String[] vaccination;
	Farm farm;
	String differentialDiagnoses;
	String regimens;//Planes terapéuticos

	
	public int getRegistry() {
		return registry;
	}

	public void setRegistry(int registry) {
		this.registry = registry;
	}

	public String getDifferentialDiagnoses() {
		return differentialDiagnoses;
	}

	public void setDifferentialDiagnoses(String differentialDiagnoses) {
		this.differentialDiagnoses = differentialDiagnoses;
	}

	public String getRegimens() {
		return regimens;
	}

	public void setRegimens(String regimens) {
		this.regimens = regimens;
	}

	public Cow(int id,int registry, String name, String breed, String birthday,
			String tattoo, Cow father, Cow mother, String finalDestination,
			String problems, int locomotionScoring,	Farm farm, String differentialDiagnoses, String regimens) {
		this.id = id;
		this.registry=registry;
		this.name = name;
		this.breed = breed;
		this.birthday = birthday;
		this.tattoo = tattoo;
		this.father = father;
		this.mother = mother;
		this.finalDestination = finalDestination;
		this.problems = problems;
		this.locomotionScoring = locomotionScoring;
		this.farm = farm;
		this.differentialDiagnoses = differentialDiagnoses;
		this.regimens = regimens;
	}

	public Cow (){
		
	}
	
	public Cow(int id, String name, String breed, String birthday, String tattoo,
			String problems, Farm farm) {
		this.id = id;
		this.name = name;
		this.breed = breed;
		this.birthday = birthday;
		this.tattoo = tattoo;
		this.problems = problems;
		this.farm = farm;
	}
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

	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
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
