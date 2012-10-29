package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SelectHoof extends Activity{

	ImageButton ibFrontLeftHoof;
	ImageButton ibFrontRightHoof;
	ImageButton ibRearLeftHoof;
	ImageButton ibRearRightHoof;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_hoof);
		
		ibFrontLeftHoof = (ImageButton)findViewById(R.id.ibFrontLeftHoof);
		ibFrontRightHoof = (ImageButton)findViewById(R.id.ibFrontRightHoof);
		ibRearLeftHoof = (ImageButton)findViewById(R.id.ibRearLeftHoof);
		ibRearRightHoof = (ImageButton)findViewById(R.id.ibRearRightHoof);
		
		
		ibFrontLeftHoof.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectHoof(v);
			}
		});
		ibFrontRightHoof.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectHoof(v);
			}
		});
		ibRearLeftHoof.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectHoof(v);
			}
		});
		ibRearRightHoof.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectHoof(v);
			}
		});
		
	}
	
	public void selectHoof(View view){
		Intent intent = new Intent("com.tpi.sagal.INJURYREGISTRATION");
		startActivity(intent);
	}

}
