package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SelectHoof extends Activity implements View.OnClickListener{

	ImageButton ibFrontLeftHoof,ibFrontRightHoof,ibRearLeftHoof,ibRearRightHoof;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_hoof);
		
		initialize();
	}
	
	public void initialize(){
		ibFrontLeftHoof = (ImageButton)findViewById(R.id.ibFrontLeftHoof);
		ibFrontRightHoof = (ImageButton)findViewById(R.id.ibFrontRightHoof);
		ibRearLeftHoof = (ImageButton)findViewById(R.id.ibRearLeftHoof);
		ibRearRightHoof = (ImageButton)findViewById(R.id.ibRearRightHoof);
		
		ibFrontLeftHoof.setOnClickListener(this);
		ibFrontRightHoof.setOnClickListener(this);
		ibRearLeftHoof.setOnClickListener(this);
		ibRearRightHoof.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		i = new Intent("com.tpi.sagal.INJURYREGISTRATION");
		startActivity(i);
	}
}