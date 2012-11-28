package com.tpi.sagal;

import com.tpi.sagal.control.ManageVaccine;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateVaccine extends Activity implements View.OnClickListener{

	Button ok, cancel;
	boolean diditwork;
	Intent i;
	EditText vacName;
	ManageVaccine mv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_vaccine);
		initialize();
	}
	
	public void initialize(){
		diditwork=true;
		mv = new ManageVaccine(this);
		ok = (Button) findViewById(R.id.bOkCreateVaccineView);
		cancel = (Button) findViewById(R.id.bCancelCreateVaccineView);
		vacName = (EditText) findViewById(R.id.etVaccineName);

		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	public boolean checkValues(){
		boolean control = true;

		if (vacName.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Nombre", Toast.LENGTH_LONG)
					.show();
			return control = false;
		}

		return control;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.bOkCreateVaccineView:
			if (checkValues()) {
				String name = vacName.getText().toString();

				try {
					mv.createVaccine(name);
				} catch (Exception e) {
					diditwork = false;
				} finally {
					if (diditwork) {
						Toast.makeText(getApplicationContext(),
								"¡Listo! Vacuna registrado con éxito.",
								Toast.LENGTH_LONG).show();
						i = new Intent(CreateVaccine.this, ViewVaccines.class);
						startActivity(i);
					}
					if (diditwork == false) {
						Toast.makeText(getApplicationContext(),
								"Error! La vacuna no fue registrada.",
								Toast.LENGTH_LONG).show();
					}
				}
			}
			
			break;
		case R.id.bCancelCreateVaccineView: 
			Toast.makeText(getApplicationContext(), "Registro cancelado.",
					Toast.LENGTH_LONG).show();
			i = new Intent(CreateVaccine.this, ViewVaccines.class);
			startActivity(i);
			break;
		}
		
	}

}
