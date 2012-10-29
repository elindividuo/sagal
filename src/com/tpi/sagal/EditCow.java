package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditCow extends Activity{

	Button ok, cancel;
	EditText etCowName, etCowNumber, etCowOwner, etCowBreed, etCowBirthday;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_cow);
		
		ok = (Button)findViewById(R.id.bOkEditCow);
		cancel = (Button)findViewById(R.id.bCancelEditCow);
		etCowName = (EditText)findViewById(R.id.etCowName_EditView);
		etCowNumber = (EditText)findViewById(R.id.etCowNumber_EditView);
		etCowOwner = (EditText)findViewById(R.id.etCowOwner_EditView);
		etCowBreed = (EditText)findViewById(R.id.etCowBreed_EditView);
		etCowBirthday = (EditText)findViewById(R.id.etCowBirthday_EditView);
		
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
					    "¡Listo! Ejemplar editado.", Toast.LENGTH_LONG)
					    .show();
				ok(v);
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
					    "No se ha modificado ningún dato.", Toast.LENGTH_LONG)
					    .show();
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
