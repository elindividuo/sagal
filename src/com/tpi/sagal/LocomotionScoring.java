package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class LocomotionScoring extends Activity{

	Button ok, cancel;
	Spinner spinnerLocScoring;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locomotion_scoring);
		
		spinnerLocScoring=(Spinner)findViewById(R.id.spinnerLocScoring);
		ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource(this, R.array.locScoring, android.R.layout.simple_spinner_item);
		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerLocScoring.setAdapter(spinner_adapter);
		
		ok = (Button)findViewById(R.id.bOkLocScoring);
		cancel = (Button)findViewById(R.id.bCancelLocScoring);
		
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						"¡Listo! Puntaje registrado.",  Toast.LENGTH_LONG)
						.show();
				ok(v);
			}
		});
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cancel(v);
			}
		});
		
	}
	
	public void ok(View view){
		Intent intent = new Intent("com.tpi.sagal.CATTLEDETAILS");
		startActivity(intent);
	}
	
	public void cancel(View view){
		Intent intent = new Intent("com.tpi.sagal.CATTLEDETAILS");
		startActivity(intent);
	}

}
