package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.tpi.sagal.control.ManageVaccine;
import com.tpi.sagal.entity.Vaccine;

public class EditVaccine extends Activity implements View.OnClickListener{

	Button ok, cancel;
	boolean diditwork;
	Intent i;
	EditText vacName;
	ManageVaccine mv;
	Vaccine vacc;
	int vaccineId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_vaccine);
		initialize();
	}
	
	public void initialize(){
		diditwork=true;
		mv = new ManageVaccine(this);
		ok = (Button) findViewById(R.id.bOkEditVaccineView);
		cancel = (Button) findViewById(R.id.bCancelEditVaccineView);
		vacName = (EditText) findViewById(R.id.etVaccineNameEdit);
		vacc= new Vaccine();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			vaccineId = extras.getInt("VACCINE_ID");
		}
		vacc=mv.searchVaccine(vaccineId);
		
		vacName.setText(vacc.getName());
		
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
		case R.id.bOkEditVaccineView:
			if (checkValues()) {
				String name = vacName.getText().toString();

				try {
					mv.updateVaccine(vaccineId,name);
				} catch (Exception e) {
					diditwork = false;
				} finally {
					if (diditwork) {
						Toast.makeText(getApplicationContext(),
								"¡Listo! Medicina editada con éxito.",
								Toast.LENGTH_LONG).show();
						i = new Intent(EditVaccine.this, ViewVaccines.class);
						startActivity(i);
					}
					if (diditwork == false) {
						Toast.makeText(getApplicationContext(),
								"Error! La medicina no fue editada.",
								Toast.LENGTH_LONG).show();
					}
				}
			}
			
			break;
		case R.id.bCancelEditVaccineView: 
			Toast.makeText(getApplicationContext(), "No se modificó ningún dato.",
					Toast.LENGTH_LONG).show();
			i = new Intent(EditVaccine.this, ViewVaccines.class);
			startActivity(i);
			break;
		}
		
	}
}
