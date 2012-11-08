package com.tpi.sagal;

import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddNotes extends Activity implements View.OnClickListener {

	Button ok;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_notes);
		initialize();
	}

	public void initialize() {
		ok = (Button) findViewById(R.id.bOkAddNotes);
		ok.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bOkAddNotes:
			i = new Intent("com.tpi.sagal.INJURYREGISTRATION");
			startActivity(i);
			break;
		}
	}

}
