package com.tpi.sagal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class InjuryRegistration extends Activity{

	Spinner spinnerHoofSections;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_injury_registration);
		
		spinnerHoofSections = (Spinner)findViewById(R.id.spinnerHoofSections);
		ArrayAdapter spinner_adapter= ArrayAdapter.createFromResource(this, R.array.hoofSections, android.R.layout.simple_spinner_item);
		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerHoofSections.setAdapter(spinner_adapter);
	}
	
	

}
