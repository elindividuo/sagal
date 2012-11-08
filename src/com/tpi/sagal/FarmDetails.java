package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FarmDetails extends Activity implements View.OnClickListener{

	Button viewCattle;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_farm_details);
		initialize();
	}
	
	public void initialize(){
		viewCattle = (Button)findViewById(R.id.bViewCattle);
		viewCattle.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.bViewCattle:
			i = new Intent("com.tpi.sagal.VIEWCATTLE");
			startActivity(i);
			break;
		}
	}
}
