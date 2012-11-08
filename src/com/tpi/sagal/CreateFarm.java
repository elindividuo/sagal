package com.tpi.sagal;

import com.tpi.sagal.control.ManageFarm;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateFarm extends Activity implements View.OnClickListener {

	Button ok;
	EditText farmName, farmOwner,farmAddress;
	Intent i;
	boolean diditwork;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_farm);
		initialize();
	}

	public void initialize() {
		ok = (Button) findViewById(R.id.bOkCreateFarmView);
		
		farmName = (EditText) findViewById(R.id.etFarmName);
		farmOwner = (EditText) findViewById(R.id.etFarmOwner);
		farmAddress = (EditText) findViewById(R.id.etFarmAddress);
		
		ok.setOnClickListener(this);
		
		diditwork=true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bOkCreateFarmView:
			String name = farmName.getText().toString();
			String address = farmAddress.getText().toString();
			String owner = farmOwner.getText().toString();
			try {
			ManageFarm mf = new ManageFarm (this);
			mf.createFarm(name, address, owner);
			}
			catch(Exception e){
				diditwork = false;
			}finally{
				if (diditwork) {
					Toast.makeText(getApplicationContext(), "¡Listo! Hacienda creada.",
							Toast.LENGTH_LONG).show();
					i = new Intent(this, MainActivityViewFarms.class);
					startActivity(i);
				}
				if (diditwork == false) {
					Toast.makeText(getApplicationContext(), "Error! La hacienda no fue creada",
							Toast.LENGTH_LONG).show();
				}
			}
			break;
		}
	}
}