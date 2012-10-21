package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CreateFarm extends Activity{

	Button ok;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_farm);
		ok = (Button)findViewById(R.id.bOkCreateFarmView);
		
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
			    "¡Listo! Hacienda creada.", Toast.LENGTH_LONG)
			    .show();
				ok(v);
			}
		});
	}
	
	
	public void ok(View view){
    	Intent intent = new Intent(this, MainActivityViewFarms.class);
    	startActivity(intent);
	}
}
