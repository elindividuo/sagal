package com.tpi.sagal;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class InjuryRegistration extends Activity{

	private CharSequence[] injuries = {"(C) Pezuña de Tirabuzón","(D) Dermatitis Digital",
			"(E) Erosión del Talón","(F) Gabarro o Flemón",
			"(G) Fisura o Grieta Horizontal","(H) Hemorragia de la Suela",
			"(I) Dermatitis Interdigital","(K) Hiperplasia Interdigital",
			"(T) Úlcera de Punta", "(U) Úlcera de la Suela",
			"(V) Fisura o Grieta Vertical","(W) Lesión de la Línea Blanca",
			"(X) Fisura Axial", "(Z) Suela Delgada"};
	private ArrayList<CharSequence> selectedInjuries = new ArrayList<CharSequence>();

	Spinner spinnerHoofSections;
	Button selectInjuries, addNotes, ok;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_injury_registration);
		
		spinnerHoofSections = (Spinner)findViewById(R.id.spinnerHoofSections);
		ArrayAdapter spinner_adapter= ArrayAdapter.createFromResource(this, R.array.hoofSections, android.R.layout.simple_spinner_item);
		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerHoofSections.setAdapter(spinner_adapter);
		
		selectInjuries = (Button)findViewById(R.id.bSelectInjuries);
		addNotes = (Button)findViewById(R.id.bAddNotes);
		ok = (Button)findViewById(R.id.bOkInjuryRegistration);
		
		selectInjuries.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showSelectInjuries();
			}
		});
		addNotes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addNotes(v);
			}
		});
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						"¡Listo! Enfermedades registradas.", Toast.LENGTH_LONG)
					    .show();
				ok(v);
			}
		});
		
	}
	
	
	public void showSelectInjuries(){
		boolean[] checkedInjuries = new boolean[injuries.length];
		int count = injuries.length;
		
		for(int i = 0; i < count; i++)
			checkedInjuries[i] = selectedInjuries.contains(injuries[i]);
		
		DialogInterface.OnMultiChoiceClickListener injuriesDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
					selectedInjuries.add(injuries[which]);
				else
					selectedInjuries.remove(injuries[which]);
				
				onChangeSelectedInjuries();
			}
		};
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Agregar enfermedades: ");
		builder.setMultiChoiceItems(injuries, checkedInjuries, injuriesDialogListener);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		AlertDialog dial = builder.create();
		dial.show();
		
	}
	
	
	public void onChangeSelectedInjuries(){
		StringBuilder stringBuilder = new StringBuilder();

		for(CharSequence injury : selectedInjuries)
			stringBuilder.append(injury + ",");
		
		Toast.makeText(getApplicationContext(),
				stringBuilder.toString(), Toast.LENGTH_LONG)
			    .show();
	}
	
	public void addNotes(View v){
		Intent intent = new Intent ("com.tpi.sagal.ADDNOTES");
		startActivity(intent);
	}
	
	public void ok(View v){
		Intent intent = new Intent ("com.tpi.sagal.SELECTHOOF");
		startActivity(intent);
	}

}
