package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CreateCow extends Activity implements View.OnClickListener {

	Button ok;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_cow);
		initialize();
	}

	public void initialize() {
		ok = (Button) findViewById(R.id.bOkCreateCow);
		ok.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bOkCreateCow:
			Toast.makeText(getApplicationContext(), "¡Listo! Ejemplar creado.",
					Toast.LENGTH_LONG).show();
			i = new Intent(this, ViewCattle.class);
			startActivity(i);
			break;
		}
	}
}
