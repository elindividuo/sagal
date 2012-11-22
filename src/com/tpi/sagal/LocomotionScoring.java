package com.tpi.sagal;

import java.util.Calendar;

import com.tpi.sagal.control.ManageCow;
import com.tpi.sagal.control.ManageLocomotionScore;

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
	int cowId, farmId, year, month, day;
	ManageCow mc;
	ManageLocomotionScore ml;
	boolean diditwork;
	String date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locomotion_scoring);
		initialize();
	}

	public void initialize() {
		diditwork = true;
		mc = new ManageCow(this);
		ml = new ManageLocomotionScore(this);
		
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		StringBuilder a = new StringBuilder().append(day).append("/")
				.append(month + 1).append("/").append(year);
		date = a.toString();

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
			try {
				mc.updateCow(cowId, Integer.parseInt(spinnerLocScoring
						.getSelectedItem().toString()));
				ml.createLocomotion(Integer.parseInt(spinnerLocScoring
						.getSelectedItem().toString()), date, cowId);
			} catch (Exception e) {
				diditwork = false;
			} finally {
				if (diditwork) {
					Toast.makeText(getApplicationContext(),
							"¡Listo! Puntaje registrado.", Toast.LENGTH_LONG)
							.show();
					i = new Intent(LocomotionScoring.this, CattleDetails.class);
					i.putExtra("COW_ID", cowId);
					i.putExtra("FARM_ID", farmId);
					startActivity(i);
				}
				if (diditwork == false) {
					Toast.makeText(getApplicationContext(),
							"Error! El puntaje no fue registrado",
							Toast.LENGTH_LONG).show();
				}
			}
			break;
		case R.id.bCancelLocScoring:
			Toast.makeText(getApplicationContext(),
					"No se ha modificado el puntaje de locomoción",
					Toast.LENGTH_LONG).show();
			i = new Intent(LocomotionScoring.this, CattleDetails.class);
			i.putExtra("COW_ID", cowId);
			i.putExtra("FARM_ID", farmId);
			startActivity(i);
			break;
		}
	}
}