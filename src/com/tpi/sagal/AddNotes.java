package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddNotes extends Activity{

	Button ok;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_notes);
		
		ok = (Button)findViewById(R.id.bOkAddNotes);
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ok(v);
			}
		});
	}
	
	
	public void ok(View view){
		Intent intent = new Intent("com.tpi.sagal.INJURYREGISTRATION");
		startActivity(intent);
	}
	

}
