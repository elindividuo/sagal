package com.tpi.sagal;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tpi.sagal.control.ManageCow;
import com.tpi.sagal.control.ManageCow_Vaccine;
import com.tpi.sagal.control.ManageVaccine;
import com.tpi.sagal.entity.Cow;
import com.tpi.sagal.entity.Vaccine;

import datepicker.MyDatePickerDialog;

public class EditCow extends Activity implements View.OnClickListener {

	Button ok, cancel,vaccine;
	EditText etCowName, etCowNumber, etCowBreed, etCowTattoo, etCowProblems,
			etCowFinalDesti;
	Intent i;
	TextView cowBirth, cowFather, cowMother;
	ImageButton changeDate, changeFather, changeMother;
	ManageCow mc;
	static final int DATE_DIALOG_ID = 999;
	private int year, month, day;
	int cowId, farmId, idFatherCow, idMotherCow;
	boolean diditwork;
	ArrayList<Cow> cows;
	ArrayList<String> cowNames, vaccineNames,existingVaccines;
	ArrayList<Vaccine> vaccines;
	ArrayList<Integer> idVaccines;
	ManageVaccine mv;
	ManageCow_Vaccine mcv;
	
	private ArrayList<CharSequence> selectedVaccines = new ArrayList<CharSequence>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_cow);
		initialize();
	}

	public void initialize() {
		mc = new ManageCow(this);
		mv = new ManageVaccine(this);
		mcv = new ManageCow_Vaccine(this);
		diditwork = true;

		vaccineNames = new ArrayList<String>();
		cowNames = new ArrayList<String>();
		existingVaccines = new ArrayList<String>();
		cows = new ArrayList<Cow>();
		vaccines = new ArrayList<Vaccine>();
		idVaccines = new ArrayList<Integer>();

		
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		Cow cow = new Cow();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			cowId = extras.getInt("COW_ID");
			farmId = extras.getInt("FARM_ID");
		}
		
		existingVaccines = mcv.searchCow_Vaccine(cowId);

		if(!existingVaccines.isEmpty()){
			final CharSequence[] items3 = existingVaccines.toArray(new CharSequence[existingVaccines.size()]);
			
			for (CharSequence f: items3){
				selectedVaccines.add(f.toString());
			}
			
		}
	
		cow = mc.searchCow(cowId);

		ok = (Button) findViewById(R.id.bOkEditCow);
		cancel = (Button) findViewById(R.id.bCancelEditCow);
		vaccine =(Button) findViewById(R.id.bCowVaccines_Edit);

		etCowName = (EditText) findViewById(R.id.etCowName_EditView);
		etCowNumber = (EditText) findViewById(R.id.etCowNumber_EditView);
		etCowBreed = (EditText) findViewById(R.id.etCowBreed_EditView);
		etCowTattoo = (EditText) findViewById(R.id.etCowTattooEdit);
		etCowProblems = (EditText) findViewById(R.id.etCowProblemsEdit);
		etCowFinalDesti = (EditText) findViewById(R.id.etCowFinalDestEdit);

		cowBirth = (TextView) findViewById(R.id.tvCowDateEdit);
		cowFather = (TextView) findViewById(R.id.tvCowFatherEdit);
		cowMother = (TextView) findViewById(R.id.tvCowMotherEdit);

		changeDate = (ImageButton) findViewById(R.id.ibChangeDateEditCow);
		changeFather = (ImageButton) findViewById(R.id.ibChangeFatherEditCow);
		changeMother = (ImageButton) findViewById(R.id.ibChangeMotherEditCow);

		etCowName.setText(cow.getName());
		etCowNumber.setText("" + cow.getRegistry());
		etCowBreed.setText(cow.getBreed());
		etCowTattoo.setText(cow.getTattoo());
		etCowProblems.setText(cow.getProblems());
		etCowFinalDesti.setText(cow.getFinalDestination());

		cowBirth.setText(cow.getBirthday());

		if (cow.getFather() == null) {
			cowFather.setText("Ninguno asignado");
		} else {
			cowFather.setText(cow.getFather().getName());
			idFatherCow = cow.getFather().getId();
		}

		if (cow.getMother() == null) {
			cowMother.setText("Ninguno asignado");
		} else {
			cowMother.setText(cow.getMother().getName());
			idMotherCow = cow.getMother().getId();
		}

		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
		changeDate.setOnClickListener(this);
		changeFather.setOnClickListener(this);
		changeMother.setOnClickListener(this);
		vaccine.setOnClickListener(this);
	}

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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bOkEditCow:
			idVaccines.clear();
			int registry = Integer.parseInt(etCowNumber.getText().toString());
			String name = etCowName.getText().toString();
			String breed = etCowBreed.getText().toString();
			String tattoo = etCowTattoo.getText().toString();
			String problems = etCowProblems.getText().toString();
			String finalDest = etCowFinalDesti.getText().toString();
			String birth = cowBirth.getText().toString();

			if (cowFather.getText().equals("Ninguno Asignado")) {
				idFatherCow = 0;
			}

			if (cowMother.getText().equals("Ninguno Asignado")) {
				idMotherCow = 0;
			}

			try {
				mc.updateCow(cowId, registry, name, breed, birth, tattoo,
						problems, finalDest, idFatherCow, idMotherCow, farmId);
				
				
				if(!selectedVaccines.isEmpty()){
					for (CharSequence f: selectedVaccines){
						Vaccine a = mv.searchVaccine(f.toString());
						idVaccines.add(a.getId());	
					}
					mcv.deleteCow_Vaccine(cowId);
					for (Integer f: idVaccines){
						mcv.createCow_Vaccine(cowId, f);
					}
				}else{
					mcv.deleteCow_Vaccine(cowId);
				}
				
				
			} catch (Exception e) {
				diditwork = false;
			} finally {
				if (diditwork) {
					Toast.makeText(getApplicationContext(),
							"¡Listo! Ejemplar editado.", Toast.LENGTH_LONG)
							.show();
					i = new Intent(EditCow.this, CattleDetails.class);
					i.putExtra("FARM_ID", farmId);
					i.putExtra("COW_ID", cowId);
					startActivity(i);
				}
				if (diditwork == false) {
					Toast.makeText(getApplicationContext(),
							"Error! El ejemplar no fue editado",
							Toast.LENGTH_LONG).show();
				}
			}
			break;
		case R.id.bCancelEditCow:
			Toast.makeText(getApplicationContext(),
					"No se ha modificado ningún dato.", Toast.LENGTH_LONG)
					.show();
			i = new Intent(EditCow.this, CattleDetails.class);
			i.putExtra("FARM_ID", farmId);
			i.putExtra("COW_ID", cowId);
			startActivity(i);
			break;
		case R.id.ibChangeDateEditCow:
			showDialog(DATE_DIALOG_ID);
			break;
		case R.id.ibChangeFatherEditCow:
			cows.clear();
			cowNames.clear();
			cows = mc.readCowFromFarm(cowId, idFatherCow, idMotherCow, farmId);

			for (Cow f : cows) {
				cowNames.add(f.getName());
			}

			final CharSequence[] items = cowNames
					.toArray(new CharSequence[cowNames.size()]);

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Selecciona el padre");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					idFatherCow = cows.get(item).getId();
					cowFather.setText(items[item]);
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
			break;
		case R.id.ibChangeMotherEditCow:
			cows.clear();
			cowNames.clear();
			cows = mc.readCowFromFarm(cowId, idFatherCow, idMotherCow, farmId);

			for (Cow f : cows) {
				cowNames.add(f.getName());
			}

			final CharSequence[] items1 = cowNames
					.toArray(new CharSequence[cowNames.size()]);

			AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
			builder1.setTitle("Selecciona a la madre");
			builder1.setItems(items1, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					idMotherCow = cows.get(item).getId();
					cowMother.setText(items1[item]);
				}
			});
			AlertDialog alert1 = builder1.create();
			alert1.show();
			break;
		case R.id.bCowVaccines_Edit:
			vaccines.clear();
			vaccineNames.clear();
			vaccines = mv.readAllVaccines();
			
			for (Vaccine f : vaccines) {
				vaccineNames.add(f.getName());
			}

			final CharSequence[] items2 = vaccineNames.toArray(new CharSequence[vaccineNames.size()]);
			boolean[] checkedInjuries = new boolean[items2.length];

			for (int i = 0; i < items2.length; i++)
				checkedInjuries[i] = selectedVaccines.contains(items2[i]);

			DialogInterface.OnMultiChoiceClickListener vaccinesDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which,
						boolean isChecked) {
					if (isChecked){
						selectedVaccines.add(items2[which]);
					}else{
						selectedVaccines.remove(items2[which]);
					}
				}
			};

			AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			builder2.setTitle("Seleccionar vacunas");
			builder2.setMultiChoiceItems(items2, checkedInjuries,
					vaccinesDialogListener);
			builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			AlertDialog dial = builder2.create();
			dial.show();
			break;
		}
	}
	
}