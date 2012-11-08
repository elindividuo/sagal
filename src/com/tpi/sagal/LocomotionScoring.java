package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class LocomotionScoring extends Activity implements View.OnClickListener {

	Button ok, cancel;
	Spinner spinnerLocScoring;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locomotion_scoring);
		initialize();
	}

	public void initialize() {
		spinnerLocScoring = (Spinner) findViewById(R.id.spinnerLocScoring);
		ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource(this,
				R.array.locScoring, android.R.layout.simple_spinner_item);
		spinner_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerLocScoring.setAdapter(spinner_adapter);

		ok = (Button) findViewById(R.id.bOkLocScoring);
		cancel = (Button) findViewById(R.id.bCancelLocScoring);

		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bOkLocScoring:
			Toast.makeText(getApplicationContext(),
					"¡Listo! Puntaje registrado.", Toast.LENGTH_LONG).show();
			i = new Intent("com.tpi.sagal.CATTLEDETAILS");
			startActivity(i);
			break;
		case R.id.bCancelLocScoring:
			i = new Intent("com.tpi.sagal.CATTLEDETAILS");
			startActivity(i);
			break;
		}

	}

}
