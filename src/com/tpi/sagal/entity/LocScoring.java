package com.tpi.sagal.entity;

public class LocScoring {
	
	int locomotionScoring;
	String date;
	int cowId;
	
	public LocScoring (int ls, String d, int id){
		this.cowId = id;
		this.locomotionScoring = ls;
		this.date = d;
	}
	
	public int getLocomotionScoring() {
		return locomotionScoring;
	}
	public void setLocomotionScoring(int locomotionScoring) {
		this.locomotionScoring = locomotionScoring;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getCowId() {
		return cowId;
	}
	public void setCowId(int cowId) {
		this.cowId = cowId;
	}

}
