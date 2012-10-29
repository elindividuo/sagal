package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CattleDetails extends Activity{

	Button ok, bHoofInjury, bLocomotionScoring, bEditCowDetails, bDeleteCow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cattle_details);
		
		ok = (Button)findViewById(R.id.bOkDetailsCow);
		bHoofInjury = (Button)findViewById(R.id.bHoofInjury);
		bLocomotionScoring = (Button)findViewById(R.id.bLocomotionScoring);
		bEditCowDetails = (Button)findViewById(R.id.bEditCowDetails);
		bDeleteCow = (Button)findViewById(R.id.bDeleteCow);
		
		ok.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ok(v);
			}
		});
		
		bHoofInjury.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				registerHoofInjury(v);
			}
		});
		
		bLocomotionScoring.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				registerLocomotionScoring(v);
			}
		});
		
		bEditCowDetails.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editCowDetails(v);
			}
		});
		
		bDeleteCow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						"¡Listo! Ejemplar eliminado.", Toast.LENGTH_LONG)
					    .show();
				deleteCow(v);
			}
		});
	}
	
	public void ok(View view){
		Intent intent = new Intent("com.tpi.sagal.VIEWCATTLE");
		startActivity(intent);
	}
	
	public void registerHoofInjury (View view){
		Intent intent = new Intent("com.tpi.sagal.SELECTHOOF");
		startActivity(intent);
	}
	
	public void registerLocomotionScoring(View view){
		Intent intent = new Intent ("com.tpi.sagal.LOCOMOTIONSCORING");
		startActivity(intent);
	}

	public void editCowDetails(View view){
		Intent intent = new Intent("com.tpi.sagal.EDITCOW");
		startActivity(intent);
	}
	
	public void deleteCow(View view){
		Intent intent = new Intent("com.tpi.sagal.VIEWCATTLE");
		startActivity(intent);
	}
	
}
