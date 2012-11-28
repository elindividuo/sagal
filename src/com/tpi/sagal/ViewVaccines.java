package com.tpi.sagal;

import java.util.ArrayList;
import com.tpi.sagal.control.ManageVaccine;
import com.tpi.sagal.entity.Vaccine;
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

public class ViewVaccines extends Activity implements View.OnClickListener{

	ListView listViewVaccines;
	ImageButton back;
	Button createVaccine;
	ArrayList<Vaccine> vaccines;
	ArrayList<String> vaccineNames;
	ManageVaccine mv;
	Intent i;
	private CharSequence[] options = { "Editar",
			"Borrar"};
	int id_vaccine;
	boolean diditwork;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_vaccines);
		initialize();
	}

	public void initialize(){
		mv = new ManageVaccine(this);
		createVaccine = (Button) findViewById(R.id.bCreateVaccine);
		back = (ImageButton) findViewById(R.id.ibBack_ViewVaccines);
		listViewVaccines = (ListView) findViewById(R.id.lvVaccines);
		
		vaccines = new ArrayList<Vaccine>();
		vaccineNames = new ArrayList<String>();

		vaccines = mv.readAllVaccines();

		for (Vaccine vac : vaccines) {
			vaccineNames.add(vac.getName());
		}
		
		listViewVaccines.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_2, android.R.id.text1,
				vaccineNames));
		
		diditwork = true;
		
		back.setOnClickListener(this);
		createVaccine.setOnClickListener(this);
		
		listViewVaccines.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				id_vaccine = vaccines.get(position).getId();
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ViewVaccines.this);
				builder.setTitle("Selecciona lo una opción");
				builder.setItems(options, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						switch (item){
						case 0:
							i = new Intent(ViewVaccines.this,EditVaccine.class);
							i.putExtra("VACCINE_ID", id_vaccine);
							startActivity(i);
							break;
						case 1:
							deleteVaccine();							
							break;
						}
					}
				});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});	
	}
	
	public void deleteVaccine(){
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setMessage("¿Seguro que quieres borrar esta vacuna?");
		adb.setCancelable(false);
		adb.setPositiveButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				try {
					mv.deleteVaccine(id_vaccine);
				} catch (Exception e) {
					diditwork = false;
				} finally {
					if (diditwork) {
						Toast.makeText(getApplicationContext(),
								"¡Listo! Vacuna borrada.",
								Toast.LENGTH_LONG).show();
						i = new Intent(ViewVaccines.this,ViewVaccines.class);
						startActivity(i);
					}
					if (diditwork == false) {
						Toast.makeText(getApplicationContext(),
								"Error! La vacuna no fue borrada",
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
		case R.id.bCreateVaccine:
			i = new Intent(ViewVaccines.this, CreateVaccine.class);
			startActivity(i);
			break;
		case R.id.ibBack_ViewVaccines:
			i = new Intent(ViewVaccines.this, MainActivityViewFarms.class);
			startActivity(i);
			break;
		}
	}
}