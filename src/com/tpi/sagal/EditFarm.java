package com.tpi.sagal;

import com.tpi.sagal.control.ManageFarm;
import com.tpi.sagal.entity.Farm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditFarm extends Activity implements View.OnClickListener{

	EditText farmName, farmAddress, farmOwner;
	Button okEdit, cancelEdit;
	ManageFarm mf;
	boolean diditwork;
	int id;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_farm);
		initialize();
	}
	
	public void initialize(){
		okEdit = (Button) findViewById(R.id.bOkFarmEdit);
		cancelEdit = (Button) findViewById(R.id.bCancelFarmEdit);
		
		farmName = (EditText) findViewById(R.id.etFarmNameEdit);
		farmAddress = (EditText) findViewById(R.id.etFarmAddressEdit);
		farmOwner = (EditText) findViewById(R.id.etFarmOwnerEdit);
		
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
		
		okEdit.setOnClickListener(this);
		cancelEdit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.bOkFarmEdit:
			String name = farmName.getText().toString();
			String address = farmAddress.getText().toString();
			String owner =  farmOwner.getText().toString();

			try {
				mf.updateFarm(id, name, address, owner);
			} catch (Exception e) {
				diditwork = false;
			} finally {
				if (diditwork) {
					Toast.makeText(getApplicationContext(),
							"¡Listo! Hacienda editada.", Toast.LENGTH_LONG)
							.show();
					i = new Intent(this, FarmDetails.class);
					i.putExtra("FARM_ID", id);
					startActivity(i);
				}
				if (diditwork == false) {
					Toast.makeText(getApplicationContext(),
							"Error! La hacienda no fue editada",
							Toast.LENGTH_LONG).show();
				}
			}
			break;
		case R.id.bCancelFarmEdit:
			Toast.makeText(getApplicationContext(),
					"Acción cancelada.", Toast.LENGTH_LONG)
					.show();
			i = new Intent(this, FarmDetails.class);
			i.putExtra("FARM_ID", id);
			startActivity(i);
			break;
		}
		
	}
}
