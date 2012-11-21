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
	int cowId, farmId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locomotion_scoring);
		initialize();
	}

	public void initialize() {
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			cowId = extras.getInt("COW_ID");
			farmId = extras.getInt("FARM_ID");
		}
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
					"¡Listo! Puntaje registrado y es: "+Integer.parseInt(spinnerLocScoring.getSelectedItem().toString()), Toast.LENGTH_LONG).show();
			i = new Intent(LocomotionScoring.this,CattleDetails.class);
			i.putExtra("COW_ID",cowId);
			i.putExtra("FARM_ID",farmId);
			startActivity(i);
			break;
		case R.id.bCancelLocScoring:
			Toast.makeText(getApplicationContext(),
					"No se ha modificado el puntaje de locomoción", Toast.LENGTH_LONG).show();
			i = new Intent(LocomotionScoring.this,CattleDetails.class);
			i.putExtra("COW_ID",cowId);
			i.putExtra("FARM_ID",farmId);
			startActivity(i);
			break;
		}

	}

}
