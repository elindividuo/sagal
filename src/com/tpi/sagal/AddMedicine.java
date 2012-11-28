package com.tpi.sagal;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tpi.sagal.control.ManageFootbath;
import com.tpi.sagal.control.ManageMedicine;
import com.tpi.sagal.entity.Footbath;
import com.tpi.sagal.entity.Medicine;

public class AddMedicine extends Activity implements View.OnClickListener{

	ManageMedicine mm;
	ImageButton backButton, selectMedicine;
	Button computeQuantity;
	TextView footbathName, footbathVol, addMedicineDilution, medicine;
	ArrayList<Medicine> medicines;
	ArrayList<String> medicineNames;
	int footbathid, farmid, medicineId;
	boolean diditwork, done = false;
	Medicine med;
	ManageFootbath mfb;
	Footbath fb;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_medicine);
		initialize();
	}
	
	public void initialize(){
		mm = new ManageMedicine(this);
		medicines = new ArrayList<Medicine>();
		medicineNames = new ArrayList<String>();
		backButton = (ImageButton)findViewById(R.id.ibBack_AddMedicine);
		computeQuantity = (Button)findViewById(R.id.bComputeQuantity);
		footbathName = (TextView)findViewById(R.id.tvAddMedicineFootbathName);
		footbathVol = (TextView)findViewById(R.id.tvAddMedicineFootbathVol);
		addMedicineDilution = (TextView)findViewById(R.id.tvAddMedicineDilution);
		selectMedicine = (ImageButton)findViewById(R.id.ibChangeMedicine);
		medicine = (TextView)findViewById(R.id.tvMedicine);
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
		footbathVol.setText("Volumen: " + vol + " cc.");
		
		diditwork = true;
		computeQuantity.setOnClickListener(this);
		backButton.setOnClickListener(this);
		selectMedicine.setOnClickListener(this); 
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.ibChangeMedicine:
			medicines.clear();
			medicineNames.clear();
			medicines=mm.readAllMedicines();
			
			for (Medicine med:medicines){
				medicineNames.add(med.getName()+" ("+med.getConcentration()+"%)");
			}
				
			final CharSequence[] items = medicineNames
					.toArray(new CharSequence[medicineNames.size()]);
			
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Selecciona la medicina");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					medicineId = medicines.get(item).getId();
					medicine.setText(items[item]);
					med = mm.searchMedicine(medicineId);
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
			
			break;
		case R.id.bComputeQuantity:
			double quantity =0;
			try{
				quantity = mfb.computeQuantity(fb.getWidth(),fb.getDeep(), fb.getHeight(), med.getConcentration());
				mfb.updateFootbath(footbathid, fb.getName(), fb.getWidth(), fb.getDeep(), fb.getHeight(), quantity, medicineId);
				done = true;
			}catch(Exception e){
				diditwork = false;
			}finally{
				if (diditwork){
					Toast.makeText(AddMedicine.this,
							"Medicina actualizada.", Toast.LENGTH_SHORT).show();				
					addMedicineDilution.setText("Debes agregar " + quantity +med.getUnit()+ " de " + medicine.getText());
				}else{
					Toast.makeText(AddMedicine.this,
							"La medicina no fue actualizada.", Toast.LENGTH_SHORT).show();
				}
			}
			break;
		case R.id.ibBack_AddMedicine:
			if (!done){
				Toast.makeText(AddMedicine.this,
						"No se agregó ninguna medicina al pediluvio.", Toast.LENGTH_SHORT).show();
			}
			i = new Intent(AddMedicine.this, FootbathDetails.class);
			i.putExtra("FOOTBATH_ID", footbathid);
			startActivity(i);
			break;
		}
	}
}