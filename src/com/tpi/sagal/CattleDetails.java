package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CattleDetails extends Activity{

	Button ok;
	Button bHoofInjury;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cattle_details);
		
		ok = (Button)findViewById(R.id.bOkDetailsCow);
		bHoofInjury = (Button)findViewById(R.id.bHoofInjury);
		
		ok.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ok(v);
			}
		});
		
		bHoofInjury.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				registerHoofInjury(v);
			}
		});
	}
	
	public void ok(View view){
		Intent intent = new Intent("com.tpi.sagal.VIEWCATTLE");
		startActivity(intent);
	}
	
	public void registerHoofInjury (View view){
		Intent intent = new Intent("com.tpi.sagal.SELECTHOOF");
		startActivity(intent);
	}

}
