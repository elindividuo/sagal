package com.tpi.sagal;

import com.tpi.sagal.control.ManageMedicine;
import com.tpi.sagal.entity.Medicine;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditMedicine extends Activity implements View.OnClickListener{

	Button ok, cancel;
	EditText name, concentration, unit;
	ManageMedicine mm;
	boolean diditwork;
	int medicineId;
	Medicine med;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_medicine);
		initialize();
	}
	
	public void initialize(){
		mm = new ManageMedicine(this);
		diditwork=true;
		
		med = new Medicine();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			medicineId = extras.getInt("MEDICINE_ID");
		}
		
		med = mm.searchMedicine(medicineId);
		
		ok = (Button) findViewById(R.id.bOkEditMedicineView);
		cancel= (Button) findViewById(R.id.bCancelEditMedicineView);
		name= (EditText) findViewById(R.id.etMedicineNameEdit);
		concentration= (EditText) findViewById(R.id.etMedicineConcentrationEdit);
		unit = (EditText) findViewById(R.id.etMedicineUnitEdit);
		
		name.setText(med.getName());
		concentration.setText(""+med.getConcentration());
		unit.setText(med.getUnit());
		
		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	public boolean checkValues(){
		boolean control = true;

		if (name.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Nombre", Toast.LENGTH_LONG)
					.show();
			return control = false;
		}

		if (concentration.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Concentración", Toast.LENGTH_LONG)
					.show();
			return control = false;
		}
		
		if (unit.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Unidad", Toast.LENGTH_LONG)
					.show();
			return control = false;
		}


		return control;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.bOkEditMedicineView:
			if (checkValues()) {
				String mName = name.getText().toString();
				double mConcent = Double.parseDouble(concentration.getText().toString());
				String mUnit = unit.getText().toString();
				try {
					mm.udpdateMedicine(medicineId, mName, mConcent,mUnit);
				} catch (Exception e) {
					diditwork = false;
				} finally {
					if (diditwork) {
						Toast.makeText(getApplicationContext(),
								"¡Listo! Medicamento editado",
								Toast.LENGTH_LONG).show();
						i = new Intent(EditMedicine.this, ViewMedicines.class);
						startActivity(i);
					}
					if (diditwork == false) {
						Toast.makeText(getApplicationContext(),
								"Error! El medicamento no fue editado.",
								Toast.LENGTH_LONG).show();
					}
				}
			}
			break;
		case R.id.bCancelEditMedicineView:
			Toast.makeText(getApplicationContext(), "No se ha modificado ningún dato.",
					Toast.LENGTH_LONG).show();
			i = new Intent(EditMedicine.this, ViewMedicines.class);
			startActivity(i);
			break;
		}
	}

}
