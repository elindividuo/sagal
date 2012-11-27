package com.tpi.sagal;

import java.util.Calendar;

import com.tpi.sagal.control.ManageCow;

import datepicker.MyDatePickerDialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CreateCow extends Activity implements View.OnClickListener {

	Button ok, cancel;
	ImageButton changeDate;
	Intent i;
	EditText cowName, cowId, cowBreed, cowTattoo, cowProblems;
	TextView cowBirth;
	boolean diditwork;
	ManageCow mc;
	int farmId;
	static final int DATE_DIALOG_ID = 999;
	private int year;
	private int month;
	private int day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_cow);
		initialize();
	}

	public void initialize() {
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			farmId = extras.getInt("FARM_ID");
		}

		mc = new ManageCow(this);

		diditwork = true;

		ok = (Button) findViewById(R.id.bOkCreateCow);
		cancel = (Button) findViewById(R.id.bCancelCreatecow);

		changeDate = (ImageButton) findViewById(R.id.ibChangeDateCreateCow);

		cowName = (EditText) findViewById(R.id.etCowName);
		cowId = (EditText) findViewById(R.id.etCowId);
		cowBreed = (EditText) findViewById(R.id.etCowBreed);
		cowTattoo = (EditText) findViewById(R.id.etCowTattoo);
		cowProblems = (EditText) findViewById(R.id.etCowProblems);

		cowBirth = (TextView) findViewById(R.id.tvCowDate);

		ok.setOnClickListener(this);
		changeDate.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// set date picker as current date
			return new MyDatePickerDialog(this, datePickerListener, year,
					month, day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			cowBirth.setText(new StringBuilder().append(day).append("/")
					.append(month + 1).append("/").append(year).append(" "));

		}
	};

	public boolean checkValues(){
		boolean control =true;
		if(cowId.getText().toString().trim().equals("")){
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Registro",
					Toast.LENGTH_LONG).show();
			return control = false;
		}
				
		if(cowName.getText().toString().trim().equals("")){
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Nombre",
					Toast.LENGTH_LONG).show();
			return control = false;
		}

		if (cowBreed.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Raza",
					Toast.LENGTH_LONG).show();
			return control = false;

		}
		if (cowTattoo.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Tatuaje",
					Toast.LENGTH_LONG).show();
			return control = false;

		}
		if (cowBirth.getText().toString().trim().equals("dd/mm/aaaa")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Fecha de Nacimiento",
					Toast.LENGTH_LONG).show();
			return control = false;

		}
		if (cowProblems.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Problemas",
					Toast.LENGTH_LONG).show();
			return control = false;
		}
		
		
		if(Integer.parseInt(cowId.getText().toString())==mc.findRegistry(Integer.parseInt(cowId.getText().toString()),farmId)){
			Toast.makeText(getApplicationContext(),
					"Ya existe un ejemplar con ese número de registro en esta hacienda",
					Toast.LENGTH_LONG).show();
			return control = false;
			
			
		}
		return control;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bOkCreateCow:
			
			if (checkValues()) {
				String name = cowName.getText().toString();
				int registry = Integer.parseInt(cowId.getText().toString());
				String breed = cowBreed.getText().toString();
				String tattoo = cowTattoo.getText().toString();
				String birth = cowBirth.getText().toString();
				String problems = cowProblems.getText().toString();
				
				try {
					mc.createCow(registry, name, breed, birth, tattoo,
							problems, "Ninguno", "Ninguno", "Ninguno", 0, 0, 0,
							farmId);
				} catch (Exception e) {
					diditwork = false;
				} finally {
					if (diditwork) {
						Toast.makeText(getApplicationContext(),
								"¡Listo! Ejemplar registrado con éxito.",
								Toast.LENGTH_LONG).show();
						i = new Intent(CreateCow.this, ViewCattle.class);
						i.putExtra("FARM_ID", farmId);
						startActivity(i);
					}
					if (diditwork == false) {
						Toast.makeText(getApplicationContext(),
								"Error! El ejemplar no fue registrado.",
								Toast.LENGTH_LONG).show();
					}
				}
			}
			break;
		case R.id.ibChangeDateCreateCow:
			showDialog(DATE_DIALOG_ID);
			break;
		case R.id.bCancelCreatecow:
			Toast.makeText(getApplicationContext(), "Registro cancelado.",
					Toast.LENGTH_LONG).show();
			i = new Intent(CreateCow.this, ViewCattle.class);
			i.putExtra("FARM_ID", farmId);
			startActivity(i);
			break;
		}

	}
}