package com.tpi.sagal;

import java.util.ArrayList;

import com.tpi.sagal.control.ManageMedicine;
import com.tpi.sagal.entity.Medicine;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ViewMedicines extends Activity implements View.OnClickListener{
	
	ListView listViewMedicines;
	ImageButton back;
	Button createMedicine;
	ArrayList<Medicine> medicines;
	ArrayList<String> medicineNames;
	ManageMedicine mm;
	Intent i;
	int id_medicine;
	private CharSequence[] options = { "Editar",
	"Borrar"};
	boolean diditwork;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_medicines);
		initialize();
	}

	public void initialize(){
		diditwork=true;
		mm= new ManageMedicine(this);
		
		listViewMedicines = (ListView) findViewById(R.id.lvMedicines);
		
		back = (ImageButton) findViewById(R.id.ibBack_ViewMedicines);
		
		createMedicine = (Button) findViewById(R.id.bCreateMedicine);
		
		
		medicines = new ArrayList<Medicine>();
		medicineNames = new ArrayList<String>();

		medicines = mm.readAllMedicines();

		for (Medicine med : medicines) {
			medicineNames.add(med.getName());
		}
		
		listViewMedicines.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_2, android.R.id.text1,
				medicineNames));
		
		back.setOnClickListener(this);
		createMedicine.setOnClickListener(this);
		
		listViewMedicines.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					id_medicine = medicines.get(position).getId();
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ViewMedicines.this);
				builder.setTitle("Selecciona una opción");
				builder.setItems(options, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						switch (item){
						case 0:
							i = new Intent(ViewMedicines.this,EditMedicine.class);
							i.putExtra("MEDICINE_ID", id_medicine);
							startActivity(i);
							break;
						case 1:
							deleteMedicine();							
							break;
						}
					}
				});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
		
	}
	
	public void deleteMedicine(){
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setMessage("¿Seguro que quieres borrar esta medicina?");
		adb.setCancelable(false);
		adb.setPositiveButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				try {
					mm.deleteMedicine(id_medicine);
				} catch (Exception e) {
					diditwork = false;
				} finally {
					if (diditwork) {
						Toast.makeText(getApplicationContext(),
								"¡Listo! Medicina borrada.",
								Toast.LENGTH_LONG).show();
						i = new Intent(ViewMedicines.this,ViewMedicines.class);
						startActivity(i);
					}
					if (diditwork == false) {
						Toast.makeText(getApplicationContext(),
								"Error! La medicina no fue borrada",
								Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		adb.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "Acción cancelada",
						Toast.LENGTH_LONG).show();
			}
		});
		adb.show();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bCreateMedicine:
			i = new Intent(ViewMedicines.this, CreateMedicine.class);
			startActivity(i);
			break;
		case R.id.ibBack_ViewMedicines:
			i = new Intent(ViewMedicines.this, MainActivityViewFarms.class);
			startActivity(i);
			break;
		}
	}

}
