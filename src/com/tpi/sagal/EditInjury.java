package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.tpi.sagal.control.ManageInjury;
import com.tpi.sagal.entity.Injury;


public class EditInjury extends Activity implements View.OnClickListener{

	Button ok, cancel;
	boolean diditwork;
	Intent i;
	EditText injName;
	ManageInjury mi;
	RadioGroup radioSelectGroup;
	int injuryId;
	Injury inj;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_injury);
		initialize();
	}
	
	public void initialize(){
		diditwork=true;
		mi = new ManageInjury(this);
		inj = new Injury();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			injuryId = extras.getInt("INJURY_ID");
		}
		
		inj = mi.searchInjury(injuryId);
		
		
		mi = new ManageInjury(this);
		ok = (Button) findViewById(R.id.bOkEditInjuryView);
		cancel = (Button) findViewById(R.id.bCancelEditInjuryView);
		injName = (EditText) findViewById(R.id.etInjuryNameEdit);
		radioSelectGroup = (RadioGroup)findViewById(R.id.radioInfEdit);

		injName.setText(inj.getName());
		
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
		case R.id.bOkEditInjuryView:
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
					mi.updateInjury(injuryId, name, inf);
;				} catch (Exception e) {
					diditwork = false;
				} finally {
					if (diditwork) {
						Toast.makeText(getApplicationContext(),
								"¡Listo! Lesión editada.",
								Toast.LENGTH_LONG).show();
						i = new Intent(EditInjury.this, ViewInjuries.class);
						startActivity(i);
					}
					if (diditwork == false) {
						Toast.makeText(getApplicationContext(),
								"Error! La lesión no fue editada.",
								Toast.LENGTH_LONG).show();
					}
				}
			}
			
			break;
		case R.id.bCancelEditInjuryView: 
			Toast.makeText(getApplicationContext(), "No se ha modificado ningún dato.",
					Toast.LENGTH_LONG).show();
			i = new Intent(EditInjury.this, ViewInjuries.class);
			startActivity(i);
			break;
		}
	}
}
