package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tpi.sagal.control.ManageFootbath;
import com.tpi.sagal.entity.Footbath;

public class AddMedicine extends Activity implements View.OnClickListener{

	ImageButton backButton;
	Button computeQuantity;
	RadioGroup radioMedicineGroup;
	RadioButton radioMedicineButton;
	TextView footbathName, footbathVol, addMedicineDilution;
	int footbathid, farmid;
	boolean diditwork;
	
	ManageFootbath mfb;
	Footbath fb;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_medicine);
		initialize();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bComputeQuantity:
			int selectedId = radioMedicineGroup.getCheckedRadioButtonId(); // get selected radio button from radioGroup
			radioMedicineButton = (RadioButton)findViewById(selectedId); // find the radioButton by returned id
			//Toast.makeText(AddMedicine.this,
			//		radioMedicineButton.getText(), Toast.LENGTH_SHORT).show();
			
			String medicineType = radioMedicineButton.getText().toString();
			double quantity = 0;
			try{
				//Llamar método del ManageFootbath para calcular cantidad y actualizar posteriormente.
				quantity = mfb.computeQuantity(fb.getWidth(), fb.getDeep(), fb.getHeight(), radioMedicineButton.getText().toString());
				//mfb.updateFootbath(selectedId, name, width, deep, height, medicine, quantity)
				mfb.updateFootbath(footbathid, fb.getName(), fb.getWidth(), fb.getDeep(), fb.getHeight(), radioMedicineButton.getText().toString(), quantity);
			}catch(Exception e){
				diditwork = false;
			}finally{
				if (diditwork){
					String unidad = "";
					if (medicineType.equals("Formol") || medicineType.equals("Hipoclorito de Sodio"))
						unidad = "cc";
					else if (medicineType.equals("CuSO4") || medicineType.equals("OTC"))
						unidad = "gr";
					addMedicineDilution.setText("Debes agregar " + quantity + unidad + " de " + medicineType);
				}
			}

			break;
		case R.id.ibBack_AddMedicine:
			Toast.makeText(AddMedicine.this,
				"No se agregó ningún medicamento al pediluvio.", Toast.LENGTH_SHORT).show();	
			i = new Intent(AddMedicine.this, FootbathDetails.class);
			i.putExtra("FOOTBATH_ID", footbathid);
			startActivity(i);
			break;
		
		}
		
	}
	
	public void initialize(){
		backButton = (ImageButton)findViewById(R.id.ibBack_AddMedicine);
		computeQuantity = (Button)findViewById(R.id.bComputeQuantity);
		radioMedicineGroup = (RadioGroup)findViewById(R.id.radioMedicine);
		footbathName = (TextView)findViewById(R.id.tvAddMedicineFootbathName);
		footbathVol = (TextView)findViewById(R.id.tvAddMedicineFootbathVol);
		addMedicineDilution = (TextView)findViewById(R.id.tvAddMedicineDilution);
		
		fb = new Footbath();
		Bundle extras = getIntent().getExtras();
		if (extras != null){
			footbathid = extras.getInt("FOOTBATH_ID");
			farmid = extras.getInt("FARM_ID");
		}
		
		mfb = new ManageFootbath(this);
		fb = mfb.searchFootbath(footbathid);
		footbathName.setText(fb.getName());
		double vol = fb.getDeep()*fb.getHeight()*fb.getWidth();
		footbathVol.setText("Volumen: " + vol);
		
		diditwork = true;
		computeQuantity.setOnClickListener(this);
		backButton.setOnClickListener(this);
	}

}
