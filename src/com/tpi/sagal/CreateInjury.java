package com.tpi.sagal;

import com.tpi.sagal.control.ManageInjury;
import com.tpi.sagal.control.ManageMedicine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateInjury extends Activity implements View.OnClickListener{

	Button ok, cancel;
	boolean diditwork;
	Intent i;
	EditText injName;
	ManageInjury mi;
	RadioGroup radioSelectGroup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_injury);
		initialize();
	}
	
	public void initialize(){
		diditwork=true;
		
		mi = new ManageInjury(this);
		ok = (Button) findViewById(R.id.bOkCreateInjuryView);
		cancel = (Button) findViewById(R.id.bCancelCreateInjuryView);
		injName = (EditText) findViewById(R.id.etInjuryName);
		radioSelectGroup = (RadioGroup)findViewById(R.id.radioInf);

		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	public boolean checkValues(){
		boolean control = true;

		if (injName.getText().toString().trim().equals("")) {
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
		case R.id.bOkCreateInjuryView:
			if (checkValues()) {
				String name = injName.getText().toString();
				
				int selectedId = radioSelectGroup.getCheckedRadioButtonId();
				int inf =0;
				switch (selectedId){
				case R.id.radioInfec: 
					inf =1;
					break;
				case R.id.radioNoInfec:
					inf=0;
					break;
				}

				try {
					mi.createInjury(name, inf);
				} catch (Exception e) {
					diditwork = false;
				} finally {
					if (diditwork) {
						Toast.makeText(getApplicationContext(),
								"¡Listo! Lesión registrada con éxito.",
								Toast.LENGTH_LONG).show();
						i = new Intent(CreateInjury.this, ViewInjuries.class);
						startActivity(i);
					}
					if (diditwork == false) {
						Toast.makeText(getApplicationContext(),
								"Error! La lesión no fue registrada.",
								Toast.LENGTH_LONG).show();
					}
				}
			}
			
			break;
		case R.id.bCancelCreateInjuryView: 
			Toast.makeText(getApplicationContext(), "Registro cancelado.",
					Toast.LENGTH_LONG).show();
			i = new Intent(CreateInjury.this, ViewInjuries.class);
			startActivity(i);
			break;
		}
	}

}
