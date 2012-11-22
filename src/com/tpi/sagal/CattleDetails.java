package com.tpi.sagal;

import com.tpi.sagal.control.ManageCow;
import com.tpi.sagal.entity.Cow;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CattleDetails extends Activity implements View.OnClickListener {

	Button bHoofInjury, bLocomotionScoring, bEditCowDetails, bDeleteCow,
			bAddNotes, bVaccine, bNotes;
	Intent i;
	TextView registry, name, breed, tattoo, birth, problems, finalDest,
			locScore, father, mother;
	int cowId, farmId;
	ManageCow mc;
	Cow c;
	boolean diditwork;
	ImageButton backButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cattle_details);
		initialize();
	}

	public void initialize() {

		registry = (TextView) findViewById(R.id.tvCowNumber_DetailsViewValue);
		name = (TextView) findViewById(R.id.tvCowName_DetailsViewValue);
		breed = (TextView) findViewById(R.id.tvCowBreed_DetailsViewValue);
		tattoo = (TextView) findViewById(R.id.tvCowTattoo_DetailsViewValue);
		birth = (TextView) findViewById(R.id.tvCowBirthday_DetailsViewValue);
		problems = (TextView) findViewById(R.id.tvCowProblems_DetailsViewValue);
		finalDest = (TextView) findViewById(R.id.tvCowFinalD_DetailsViewValue);
		locScore = (TextView) findViewById(R.id.tvCowLocScor_DetailsViewValue);
		father = (TextView) findViewById(R.id.tvCowFather_DetailsViewValue);
		mother = (TextView) findViewById(R.id.tvCowMother_DetailsViewValue);

		backButton = (ImageButton) findViewById(R.id.ibBack_CattleDetails);

		bHoofInjury = (Button) findViewById(R.id.bHoofInjury);
		bLocomotionScoring = (Button) findViewById(R.id.bLocomotionScoring);
		bEditCowDetails = (Button) findViewById(R.id.bEditCowDetails);
		bDeleteCow = (Button) findViewById(R.id.bDeleteCow);
		bAddNotes = (Button) findViewById(R.id.bAddNotes);
		bVaccine = (Button) findViewById(R.id.bCowVaccines_Details);
		bNotes = (Button) findViewById(R.id.bCowNotes_Details);

		c = new Cow();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			cowId = extras.getInt("COW_ID");
			farmId = extras.getInt("FARM_ID");
		}

		mc = new ManageCow(this);
		c = mc.searchCow(cowId);
		diditwork = true;

		registry.setText("" + c.getRegistry());
		name.setText(c.getName());
		breed.setText(c.getBreed());
		tattoo.setText(c.getTattoo());
		birth.setText(c.getBirthday());
		problems.setText(c.getProblems());
		finalDest.setText(c.getFinalDestination());
		locScore.setText("" + c.getLocomotionScoring());
		if (c.getFather() == null) {
			father.setText("Ninguno asignado");
		} else {
			father.setText(c.getFather().getName());
		}

		if (c.getMother() == null) {
			mother.setText("Ninguno asignado");
		} else {
			mother.setText(c.getMother().getName());
		}

		bHoofInjury.setOnClickListener(this);
		bLocomotionScoring.setOnClickListener(this);
		bEditCowDetails.setOnClickListener(this);
		bDeleteCow.setOnClickListener(this);
		bAddNotes.setOnClickListener(this);
		backButton.setOnClickListener(this);
		bNotes.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibBack_CattleDetails:
			i = new Intent(CattleDetails.this, ViewCattle.class);
			i.putExtra("FARM_ID", farmId);
			startActivity(i);
			break;
		case R.id.bHoofInjury:
			i = new Intent(CattleDetails.this, SelectHoof.class);
			i.putExtra("COW_ID", cowId);
			i.putExtra("FARM_ID", farmId);
			startActivity(i);
			break;
		case R.id.bLocomotionScoring:
			i = new Intent(CattleDetails.this, LocomotionScoring.class);
			i.putExtra("COW_ID", cowId);
			i.putExtra("FARM_ID", farmId);
			startActivity(i);
			break;
		case R.id.bEditCowDetails:
			i = new Intent(CattleDetails.this, EditCow.class);
			i.putExtra("COW_ID", cowId);
			i.putExtra("FARM_ID", farmId);
			startActivity(i);
			break;
		case R.id.bDeleteCow:

			AlertDialog.Builder adb = new AlertDialog.Builder(this);
			adb.setMessage("¿Seguro que quieres borrar este ejemplar?");
			adb.setCancelable(false);
			adb.setPositiveButton("Si", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					try {
						mc.deleteCow(cowId);
					} catch (Exception e) {
						diditwork = false;
					} finally {
						if (diditwork) {
							Toast.makeText(getApplicationContext(),
									"¡Listo! Ejemplar borrado.",
									Toast.LENGTH_LONG).show();
							i = new Intent(CattleDetails.this, ViewCattle.class);
							i.putExtra("FARM_ID", farmId);
							startActivity(i);
						}
						if (diditwork == false) {
							Toast.makeText(getApplicationContext(),
									"Error! La hacienda no fue borrada",
									Toast.LENGTH_LONG).show();
						}
					}
				}
			});
			adb.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "Acción cancelada",
							Toast.LENGTH_LONG).show();
				}
			});
			adb.show();

			break;
		case R.id.bAddNotes:
			i = new Intent(CattleDetails.this, AddNotes.class);
			i.putExtra("COW_ID", cowId);
			i.putExtra("FARM_ID", farmId);
			startActivity(i);
			break;
		case R.id.bCowNotes_Details:

			AlertDialog alertDialog = new AlertDialog.Builder(this).create();

			// Setting Dialog Title
			alertDialog.setTitle("Notas del ejemplar");

			// Setting Dialog Message
			alertDialog.setMessage("Diagnóticos Diferenciales: \n"
					+ c.getDifferentialDiagnoses() + "\n"
					+ "Planes Terapéuticos: \n" + c.getRegimens());

			// Setting OK Button
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(final DialogInterface dialog,
						final int which) {
				}
			});

			// Showing Alert Message
			alertDialog.show();
			break;
		}
	}
}
