package com.tpi.sagal;

import com.tpi.sagal.control.ManageCow;
import com.tpi.sagal.entity.Cow;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNotes extends Activity implements View.OnClickListener {

	Button ok, cancel;
	Intent i;
	int cowId, farmId;
	ManageCow mc;
	EditText diffDiag, regimens;
	boolean diditwork;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_notes);
		initialize();
	}

	public void initialize() {
		diditwork = true;
		mc = new ManageCow(this);
		Cow c = new Cow();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			cowId = extras.getInt("COW_ID");
			farmId = extras.getInt("FARM_ID");
		}

		diffDiag = (EditText) findViewById(R.id.etDifferentialDiagnoses);
		regimens = (EditText) findViewById(R.id.etRegimens);

		c = mc.searchCow(cowId);

		diffDiag.setText(c.getDifferentialDiagnoses());
		regimens.setText(c.getRegimens());

		ok = (Button) findViewById(R.id.bOkAddNotes);
		cancel = (Button) findViewById(R.id.bCancelAddNotes);

		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bOkAddNotes:

			String cDiffDiag = diffDiag.getText().toString();
			String cRegimens = regimens.getText().toString();
			try {
				mc.updateCow(cowId, cRegimens, cDiffDiag);
			} catch (Exception e) {
				diditwork = false;
			} finally {
				if (diditwork) {
					Toast.makeText(getApplicationContext(),
							"¡Listo! Notas agregadas.", Toast.LENGTH_LONG)
							.show();
					i = new Intent(AddNotes.this, CattleDetails.class);
					i.putExtra("COW_ID", cowId);
					i.putExtra("FARM_ID", farmId);
					startActivity(i);
				}
				if (diditwork == false) {
					Toast.makeText(getApplicationContext(),
							"Error! Las notas no fueron agregadas",
							Toast.LENGTH_LONG).show();
				}
			}

			break;
		case R.id.bCancelAddNotes:
			Toast.makeText(getApplicationContext(),
					"No se ha modificado ningún dato.", Toast.LENGTH_LONG)
					.show();
			i = new Intent(AddNotes.this, CattleDetails.class);
			i.putExtra("COW_ID", cowId);
			i.putExtra("FARM_ID", farmId);
			startActivity(i);
			break;
		}
	}

}
