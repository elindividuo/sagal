package com.tpi.sagal;

import com.tpi.sagal.control.ManageFarm;
import com.tpi.sagal.entity.Farm;
import com.tpi.sagal.graphs.LocomotionScoringGraph;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FarmDetails extends Activity implements View.OnClickListener {

	Intent i;
	TextView farmName, farmAddress, farmOwner;
	Button editFarm, deleteFarm, cattle, foothBath, statistics, viewCattle;
	int id;
	ManageFarm mf;
	boolean diditwork;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_farm_details);
		initialize();
	}

	public void initialize() {
		editFarm = (Button) findViewById(R.id.bEditFarm);
		deleteFarm = (Button) findViewById(R.id.bDeleteFarm);
		foothBath = (Button) findViewById(R.id.bFoothbath);
		statistics = (Button) findViewById(R.id.bStatistics);
		viewCattle = (Button) findViewById(R.id.bViewCattle);

		farmName = (TextView) findViewById(R.id.tvFarmName);
		farmAddress = (TextView) findViewById(R.id.tvFarmAddress);
		farmOwner = (TextView) findViewById(R.id.tvFarmOwner);

		Farm farm = new Farm();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			id = extras.getInt("FARM_ID");
		}

		mf = new ManageFarm(this);
		farm = mf.searchFarm(id);
		diditwork = true;

		farmName.setText(farm.getName());
		farmAddress.setText(farm.getAddress());
		farmOwner.setText(farm.getOwnerName());

		editFarm.setOnClickListener(this);
		deleteFarm.setOnClickListener(this);
		foothBath.setOnClickListener(this);
		statistics.setOnClickListener(this);
		viewCattle.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bEditFarm:
			i= new Intent(FarmDetails.this,EditFarm.class);
			i.putExtra("FARM_ID", id);
			startActivity(i);

			break;
		case R.id.bDeleteFarm:

			AlertDialog.Builder adb = new AlertDialog.Builder(this);
			adb.setMessage("¿Seguro que quieres borrar esta hacienda?");
			adb.setCancelable(false);
			adb.setPositiveButton("Si", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					try {
						mf.deleteFarm(id);
					} catch (Exception e) {
						diditwork = false;
					} finally {
						if (diditwork) {
							Toast.makeText(getApplicationContext(),
									"¡Listo! Hacienda borrada.",
									Toast.LENGTH_LONG).show();
							i = new Intent(FarmDetails.this,
									MainActivityViewFarms.class);
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
		case R.id.bFoothbath:
			i = new Intent(FarmDetails.this,ViewFootbaths.class);
			i.putExtra("FARM_ID", id);
			startActivity(i);
			break;
		case R.id.bStatistics:
			LocomotionScoringGraph bar = new LocomotionScoringGraph();
			i = bar.getIntent(this);
			startActivity(i);
			break;
		case R.id.bViewCattle:
			i = new Intent("com.tpi.sagal.VIEWCATTLE");
			startActivity(i);
			break;
		}
	}
}
