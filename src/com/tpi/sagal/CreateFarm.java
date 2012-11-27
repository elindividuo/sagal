package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tpi.sagal.control.ManageFarm;

public class CreateFarm extends Activity implements View.OnClickListener {

	Button ok, cancel;
	EditText farmName, farmOwner, farmAddress;
	Intent i;
	ManageFarm mf;
	boolean diditwork;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_farm);
		initialize();
	}

	public void initialize() {
		ok = (Button) findViewById(R.id.bOkCreateFarmView);
		cancel = (Button) findViewById(R.id.bCancelCreateFarm);

		farmName = (EditText) findViewById(R.id.etFarmName);
		farmOwner = (EditText) findViewById(R.id.etFarmOwner);
		farmAddress = (EditText) findViewById(R.id.etFarmAddress);

		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);

		mf = new ManageFarm(this);
		diditwork = true;
	}

	public boolean checkValues() {
		boolean control = true;

		if (farmOwner.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Propietario",
					Toast.LENGTH_LONG).show();
			return control = false;
		}
		

		if (farmName.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Nombre", Toast.LENGTH_LONG)
					.show();
			return control = false;
		}


		if (farmAddress.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Dirección", Toast.LENGTH_LONG)
					.show();
			return control = false;
		}

		return control;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bOkCreateFarmView:
			if (checkValues()) {
				String name = farmName.getText().toString();
				String address = farmAddress.getText().toString();
				String owner = farmOwner.getText().toString();
				try {
					mf.createFarm(name, address, owner);
				} catch (Exception e) {
					diditwork = false;
				} finally {
					if (diditwork) {
						Toast.makeText(getApplicationContext(),
								"¡Listo! Hacienda registrada con éxito.",
								Toast.LENGTH_LONG).show();
						i = new Intent(CreateFarm.this,
								MainActivityViewFarms.class);
						startActivity(i);
					}
					if (diditwork == false) {
						Toast.makeText(getApplicationContext(),
								"Error! La hacienda no fue registrada.",
								Toast.LENGTH_LONG).show();
					}
				}
			}
			break;
		case R.id.bCancelCreateFarm:
			Toast.makeText(getApplicationContext(), "Registro cancelado.",
					Toast.LENGTH_LONG).show();
			i = new Intent(CreateFarm.this, MainActivityViewFarms.class);
			startActivity(i);
		}
	}
}