package com.tpi.sagal;

import com.tpi.sagal.control.ManageFootbath;
import com.tpi.sagal.entity.Footbath;
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

public class FootbathDetails extends Activity implements View.OnClickListener {

	TextView fbName, fbWidth, fbDeep, fbHeight, fbMedicine, fbQuantity;
	Button deleteFootbath, editFootbath, setMedicine;
	ManageFootbath mft;
	int id, farmid;
	boolean diditwork;
	Intent i;
	ImageButton backButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_footbath_details);
		initialize();
	}

	public void initialize() {
		fbName = (TextView) findViewById(R.id.tvFootbathName);
		fbWidth = (TextView) findViewById(R.id.tvFootbathWidth);
		fbDeep = (TextView) findViewById(R.id.tvFootbathDeep);
		fbHeight = (TextView) findViewById(R.id.tvFootbathHeight);
		fbMedicine = (TextView) findViewById(R.id.tvFootbathMedicine);
		fbQuantity = (TextView) findViewById(R.id.tvFootbathQuantity);

		editFootbath = (Button) findViewById(R.id.bEditFB);
		deleteFootbath = (Button) findViewById(R.id.bDeleteFB);
		setMedicine = (Button) findViewById(R.id.bMedicine);

		backButton = (ImageButton) findViewById(R.id.ibBack_FootbathDetails);

		Footbath fb = new Footbath();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			id = extras.getInt("FOOTBATH_ID");
		}

		mft = new ManageFootbath(this);
		fb = mft.searchFootbath(id);
		diditwork = true;

		editFootbath.setOnClickListener(this);
		deleteFootbath.setOnClickListener(this);
		setMedicine.setOnClickListener(this);
		backButton.setOnClickListener(this);

		fbName.setText(fb.getName());
		fbWidth.setText("" + fb.getWidth());
		fbDeep.setText("" + fb.getDeep());
		fbHeight.setText("" + fb.getHeight());
		fbMedicine.setText(fb.getMedicineType());
		fbQuantity.setText("" + fb.getQuantity());
		farmid = fb.getFarm().getId();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bEditFB:
			i = new Intent(FootbathDetails.this, EditFootbath.class);
			i.putExtra("FOOTBATH_ID", id);
			startActivity(i);
			break;
		case R.id.bDeleteFB:
			AlertDialog.Builder adb = new AlertDialog.Builder(this);
			adb.setMessage("Seguro que quieres borrar este pediluvio?");
			adb.setCancelable(false);
			adb.setPositiveButton("Si", new DialogInterface.OnClickListener() { // Boton
																				// Positivio
						public void onClick(DialogInterface dialog, int which) {
							try {
								mft.deleteFootbath(id);
							} catch (Exception e) {
								diditwork = false;
							} finally {
								if (diditwork) {
									Toast.makeText(getApplicationContext(),
											"¡Listo! Pediluvio borrado.",
											Toast.LENGTH_LONG).show();
									i = new Intent(FootbathDetails.this,
											ViewFootbaths.class);
									i.putExtra("FARM_ID", farmid);
									startActivity(i);
								}
								if (diditwork == false) {
									Toast.makeText(
											getApplicationContext(),
											"Error! El pediluvio no fue borrado",
											Toast.LENGTH_LONG).show();
								}
							}
						}
					});
			adb.setNegativeButton("No", new DialogInterface.OnClickListener() {// Boton
																				// negativo
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(getApplicationContext(),
									"Acción cancelada", Toast.LENGTH_LONG)
									.show();
						}
					});
			adb.show();// Se muestra el AlertDialog
			break;
		case R.id.bMedicine:
			i = new Intent (FootbathDetails.this, AddMedicine.class);
			i.putExtra("FOOTBATH_ID", id);
			i.putExtra("FARM_ID", farmid);
			startActivity(i);
			break;
		case R.id.ibBack_FootbathDetails:
			i = new Intent(FootbathDetails.this, ViewFootbaths.class);
			i.putExtra("FARM_ID", farmid);
			startActivity(i);
			break;
		}
	}

}
