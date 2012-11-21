package com.tpi.sagal;

import com.tpi.sagal.control.ManageFootbath;
import com.tpi.sagal.entity.Footbath;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditFootbath extends Activity implements View.OnClickListener{
	
	Button okButton, cancelButton;
	EditText fbName, fbWidth, fbDeep, fbHeight;
	ManageFootbath mft;
	int id;
	boolean diditwork;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_footbath);
		initialize();
	}
	
	public void initialize(){
		mft = new ManageFootbath(this);
		
		okButton = (Button) findViewById(R.id.bOkFootbathEdit);
		cancelButton = (Button) findViewById(R.id.bCancelFootbathEdit);
		fbName = (EditText) findViewById(R.id.etFootbathNameEdit);
		fbWidth = (EditText) findViewById(R.id.etFootbathWidthEdit);
		fbDeep = (EditText) findViewById(R.id.etFootbathDeepEdit);
		fbHeight = (EditText) findViewById(R.id.etFootbathHeightEdit);
		
		Footbath fb = new Footbath();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			id = extras.getInt("FOOTBATH_ID");
		}
		
		mft = new ManageFootbath(this);
		fb = mft.searchFootbath(id);
		diditwork = true;
		
		fbName.setText(fb.getName());
		fbWidth.setText(""+fb.getWidth());
		fbDeep.setText(""+fb.getDeep());
		fbHeight.setText(""+fb.getHeight());
		
		okButton.setOnClickListener(this);
		cancelButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.bOkFootbathEdit:
			String name = fbName.getText().toString();
			double width = Double.parseDouble(fbWidth.getText().toString()) ;
			double deep = Double.parseDouble(fbDeep.getText().toString());
			double height = Double.parseDouble(fbHeight.getText().toString());
			try {
				mft.updateFootbath(id, name, width, deep, height);
			} catch (Exception e) {
				diditwork = false;
			} finally {
				if (diditwork) {
					Toast.makeText(getApplicationContext(),
							"¡Listo! Pediluvio Editado.", Toast.LENGTH_LONG)
							.show();
					i = new Intent(this, FootbathDetails.class);
					i.putExtra("FOOTBATH_ID", id);
					startActivity(i);
				}
				if (diditwork == false) {
					Toast.makeText(getApplicationContext(),
							"Error! El pediluvio no fue editado",
							Toast.LENGTH_LONG).show();
				}
			}
			break;
		case R.id.bCancelFootbathEdit:
			Toast.makeText(getApplicationContext(),
					"No se ha modificado ningún dato.", Toast.LENGTH_LONG)
					.show();
			i = new Intent(this, FootbathDetails.class);
			i.putExtra("FOOTBATH_ID", id);
			startActivity(i);
			break;
		
		}
	}
}