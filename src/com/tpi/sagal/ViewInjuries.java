package com.tpi.sagal;

import java.util.ArrayList;

import com.tpi.sagal.control.ManageInjury;
import com.tpi.sagal.entity.Injury;
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

public class ViewInjuries extends Activity implements View.OnClickListener{

	ListView listViewInjuries;
	ImageButton back;
	Button createInjury;
	ArrayList<Injury> injuries;
	ArrayList<String> injuryNames;
	ManageInjury mi;
	Intent i;
	int id_injury;
	private CharSequence[] options = { "Editar",
	"Borrar"};
	boolean diditwork;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_injuries);
		initialize();
	}
	
	public void initialize(){
		
	diditwork=true;
	mi= new ManageInjury(this);
	
	listViewInjuries = (ListView) findViewById(R.id.lvInjuries);
	
	back = (ImageButton) findViewById(R.id.ibBack_ViewInjuries);
	
	createInjury = (Button) findViewById(R.id.bCreateInjury);
	
	
	injuries = new ArrayList<Injury>();
	injuryNames = new ArrayList<String>();

	injuries = mi.readAllInjuries();

	for (Injury inj : injuries) {
		injuryNames.add(inj.getName());
	}

	listViewInjuries.setAdapter(new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_2, android.R.id.text1,
			injuryNames));
	
	back.setOnClickListener(this);
	createInjury.setOnClickListener(this);
	
	listViewInjuries.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
				id_injury = injuries.get(position).getId();
			
			AlertDialog.Builder builder = new AlertDialog.Builder(ViewInjuries.this);
			builder.setTitle("Seleccionauna opción");
			builder.setItems(options, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					switch (item){
					case 0:
						i = new Intent(ViewInjuries.this,EditInjury.class);
						i.putExtra("INJURY_ID", id_injury);
						startActivity(i);
						break;
					case 1:
						deleteInjury();							
						break;
					}
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		}
	});
	
}

public void deleteInjury(){
	AlertDialog.Builder adb = new AlertDialog.Builder(this);
	adb.setMessage("¿Seguro que quieres borrar esta lesión?");
	adb.setCancelable(false);
	adb.setPositiveButton("Si", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			try {
				mi.deleteInjury(id_injury);
			} catch (Exception e) {
				diditwork = false;
			} finally {
				if (diditwork) {
					Toast.makeText(getApplicationContext(),
							"¡Listo! Lesión borrada.",
							Toast.LENGTH_LONG).show();
					i = new Intent(ViewInjuries.this,ViewInjuries.class);
					startActivity(i);
				}
				if (diditwork == false) {
					Toast.makeText(getApplicationContext(),
							"Error! La lesión no fue borrada",
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
	case R.id.bCreateInjury:
		i = new Intent(ViewInjuries.this, CreateInjury.class);
		startActivity(i);
		break;
	case R.id.ibBack_ViewInjuries:
		i = new Intent(ViewInjuries.this, MainActivityViewFarms.class);
		startActivity(i);
		break;
	}
}


}
