package com.tpi.sagal;

import com.tpi.sagal.control.ManageFootbath;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

public class CreateFootbath extends Activity implements View.OnClickListener {

	Button okButton, cancelButton;
	EditText fbName, fbWidth, fbDeep, fbHeight;
	ManageFootbath mft;
	int id;
	boolean diditwork;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_footbath);
		initialize();
	}

	public void initialize() {

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			id = extras.getInt("FARM_ID");
		}

		mft = new ManageFootbath(this);

		okButton = (Button) findViewById(R.id.bOkCreateFootbathView);
		cancelButton = (Button) findViewById(R.id.bCancelCreateFootbathView);
		fbName = (EditText) findViewById(R.id.etFootbathName);
		fbWidth = (EditText) findViewById(R.id.etFootbathWidth);
		fbDeep = (EditText) findViewById(R.id.etFootbathDeep);
		fbHeight = (EditText) findViewById(R.id.etFootbathHeight);

		okButton.setOnClickListener(this);
		cancelButton.setOnClickListener(this);

		diditwork = true;
	}

	public boolean checkValues() {
		boolean control = true;

		if (fbName.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Nombre", Toast.LENGTH_LONG)
					.show();
			return control = false;
		}

		if (fbWidth.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Ancho", Toast.LENGTH_LONG)
					.show();
			return control = false;
		}

		if (fbDeep.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Largo", Toast.LENGTH_LONG)
					.show();
			return control = false;
		}

		if (fbHeight.getText().toString().trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"Olvidaste llenar el campo de Alto", Toast.LENGTH_LONG)
					.show();
			return control = false;
		}

		return control;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bOkCreateFootbathView:
			if (checkValues()) {
				String name = fbName.getText().toString();
				double width = Double.parseDouble(fbWidth.getText().toString());
				double deep = Double.parseDouble(fbDeep.getText().toString());
				double height = Double.parseDouble(fbHeight.getText()
						.toString());
				try {
					mft.createFootbath(name, width, deep, height, "Ninguno", 0,
							id);
				} catch (Exception e) {
					diditwork = false;
				} finally {
					if (diditwork) {
						Toast.makeText(getApplicationContext(),
								"¡Listo! Pediluvio registrado con éxito.",
								Toast.LENGTH_LONG).show();
						i = new Intent(CreateFootbath.this, ViewFootbaths.class);
						i.putExtra("FARM_ID", id);
						startActivity(i);
					}
					if (diditwork == false) {
						Toast.makeText(getApplicationContext(),
								"Error! El pediluvio no fue registrado.",
								Toast.LENGTH_LONG).show();
					}
				}
			}
			break;
		case R.id.bCancelCreateFootbathView:
			Toast.makeText(getApplicationContext(), "Registro cancelado.",
					Toast.LENGTH_LONG).show();
			i = new Intent(CreateFootbath.this, ViewFootbaths.class);
			i.putExtra("FARM_ID", id);
			startActivity(i);
			break;
		}
	}

}
