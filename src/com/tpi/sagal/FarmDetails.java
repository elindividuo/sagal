package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FarmDetails extends Activity{

	Button viewCattle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_farm_details);
		
		viewCattle = (Button)findViewById(R.id.bViewCattle);
		viewCattle.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewCattle(v);
			}
		});
	}
	
	public void viewCattle(View view){
		Intent intent = new Intent("com.tpi.sagal.VIEWCATTLE");
		startActivity(intent);
	}
}
