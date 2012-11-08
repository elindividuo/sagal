package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CattleDetails extends Activity implements View.OnClickListener {

	Button ok, bHoofInjury, bLocomotionScoring, bEditCowDetails, bDeleteCow;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cattle_details);
		initialize();
	}

	public void initialize() {
		ok = (Button) findViewById(R.id.bOkDetailsCow);
		bHoofInjury = (Button) findViewById(R.id.bHoofInjury);
		bLocomotionScoring = (Button) findViewById(R.id.bLocomotionScoring);
		bEditCowDetails = (Button) findViewById(R.id.bEditCowDetails);
		bDeleteCow = (Button) findViewById(R.id.bDeleteCow);

		ok.setOnClickListener(this);
		bHoofInjury.setOnClickListener(this);
		bLocomotionScoring.setOnClickListener(this);
		bEditCowDetails.setOnClickListener(this);
		bDeleteCow.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bOkDetailsCow:
			i = new Intent("com.tpi.sagal.VIEWCATTLE");
			startActivity(i);
			break;
		case R.id.bHoofInjury:
			i = new Intent("com.tpi.sagal.SELECTHOOF");
			startActivity(i);
			break;
		case R.id.bLocomotionScoring:
			i = new Intent("com.tpi.sagal.LOCOMOTIONSCORING");
			startActivity(i);
			break;
		case R.id.bEditCowDetails:
			i = new Intent("com.tpi.sagal.EDITCOW");
			startActivity(i);
			break;
		case R.id.bDeleteCow:
			Toast.makeText(getApplicationContext(),
					"¡Listo! Ejemplar eliminado.", Toast.LENGTH_LONG).show();
			i = new Intent("com.tpi.sagal.VIEWCATTLE");
			startActivity(i);
			break;
		}
	}
}
