package com.tpi.sagal;

import com.tpi.sagal.control.ManageMedicine;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateMedicine extends Activity implements View.OnClickListener{

	Button ok, cancel;
	boolean diditwork;
	Intent i;
	EditText medName, medConcent;
	ManageMedicine mm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_medicine);
		initialize();
	}

	public void initialize(){
		
		diditwork=true;
		mm = new ManageMedicine(this);
		ok = (Button) findViewById(R.id.bOkCreateMedicineView);
		cancel = (Button) findViewById(R.id.bCancelCreateMedicineView);
		medName = (EditText) findViewById(R.id.etMedicineName);
		medConcent = (EditText) findViewById(R.id.etMedicineConcentration);

		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);

	}
	
	public boolean checkValues(){
		boolean control = true;

		if (medName.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Nombre", Toast.LENGTH_LONG)
					.show();
			return control = false;
		}

		if (medConcent.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Concentración", Toast.LENGTH_LONG)
					.show();
			return control = false;
		}

		return control;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.bOkCreateMedicineView:
			if (checkValues()) {
				String name = medName.getText().toString();
				double concent = Double.parseDouble(medConcent.getText().toString());

				try {
					mm.createMedicine(name, concent);
				} catch (Exception e) {
					diditwork = false;
				} finally {
					if (diditwork) {
						Toast.makeText(getApplicationContext(),
								"¡Listo! Medicamento registrado con éxito.",
								Toast.LENGTH_LONG).show();
						i = new Intent(CreateMedicine.this, ViewMedicines.class);
						startActivity(i);
					}
					if (diditwork == false) {
						Toast.makeText(getApplicationContext(),
								"Error! El medicamento no fue registrado.",
								Toast.LENGTH_LONG).show();
					}
				}
			}
			
			break;
		case R.id.bCancelCreateMedicineView: 
			Toast.makeText(getApplicationContext(), "Registro cancelado.",
					Toast.LENGTH_LONG).show();
			i = new Intent(CreateMedicine.this, ViewMedicines.class);
			startActivity(i);
			break;
		}
		
	}

}
