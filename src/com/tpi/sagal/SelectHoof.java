package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SelectHoof extends Activity implements View.OnClickListener {

	ImageButton ibFrontLeftHoof, ibFrontRightHoof, ibRearLeftHoof,
			ibRearRightHoof, backButton;
	Intent i;
	int cowId, farmId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_hoof);
		initialize();
	}

	public void initialize() {

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			cowId = extras.getInt("COW_ID");
			farmId = extras.getInt("FARM_ID");
		}

		ibFrontLeftHoof = (ImageButton) findViewById(R.id.ibFrontLeftHoof);
		ibFrontRightHoof = (ImageButton) findViewById(R.id.ibFrontRightHoof);
		ibRearLeftHoof = (ImageButton) findViewById(R.id.ibRearLeftHoof);
		ibRearRightHoof = (ImageButton) findViewById(R.id.ibRearRightHoof);
		backButton = (ImageButton) findViewById(R.id.ibBack_SelectHoof);

		ibFrontLeftHoof.setOnClickListener(this);
		ibFrontRightHoof.setOnClickListener(this);
		ibRearLeftHoof.setOnClickListener(this);
		ibRearRightHoof.setOnClickListener(this);
		backButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibBack_SelectHoof:
			i = new Intent(SelectHoof.this, CattleDetails.class);
			i.putExtra("COW_ID", cowId);
			i.putExtra("FARM_ID", farmId);
			startActivity(i);
		}

		i = new Intent(SelectHoof.this, InjuryRegistration.class);
		startActivity(i);
	}
}