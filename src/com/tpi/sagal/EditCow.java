package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditCow extends Activity implements View.OnClickListener{

	Button ok, cancel;
	EditText etCowName, etCowNumber, etCowOwner, etCowBreed, etCowBirthday;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_cow);
		initialize();
	}
	
	
	public void initialize(){
		ok = (Button)findViewById(R.id.bOkEditCow);
		cancel = (Button)findViewById(R.id.bCancelEditCow);
		
		etCowName = (EditText)findViewById(R.id.etCowName_EditView);
		etCowNumber = (EditText)findViewById(R.id.etCowNumber_EditView);
		etCowOwner = (EditText)findViewById(R.id.etCowOwner_EditView);
		etCowBreed = (EditText)findViewById(R.id.etCowBreed_EditView);
		etCowBirthday = (EditText)findViewById(R.id.etCowBirthday_EditView);
		
		
		
		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.bOkEditCow:
			Toast.makeText(getApplicationContext(),
				    "¡Listo! Ejemplar editado.", Toast.LENGTH_LONG)
				    .show();
			i = new Intent("com.tpi.sagal.CATTLEDETAILS");
			startActivity(i);
			break;
		case R.id.bCancelEditCow:
			Toast.makeText(getApplicationContext(),
				    "No se ha modificado ningún dato.", Toast.LENGTH_LONG)
				    .show();
			i = new Intent("com.tpi.sagal.CATTLEDETAILS");
			startActivity(i);
			break;
		}
		
	}
	
}
