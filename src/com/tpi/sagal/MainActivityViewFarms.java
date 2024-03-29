package com.tpi.sagal;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.tpi.sagal.control.ManageCow;
import com.tpi.sagal.control.ManageCow_Vaccine;
import com.tpi.sagal.control.ManageFarm;
import com.tpi.sagal.control.ManageFootbath;
import com.tpi.sagal.control.ManageHoof;
import com.tpi.sagal.control.ManageInjury;
import com.tpi.sagal.control.ManageLimb;
import com.tpi.sagal.control.ManageLocomotionScore;
import com.tpi.sagal.control.ManageMedicine;
import com.tpi.sagal.control.ManageVaccine;
import com.tpi.sagal.control.ManageZone;
import com.tpi.sagal.control.ManageZone_Injury;
import com.tpi.sagal.entity.Farm;

public class MainActivityViewFarms extends Activity implements
		View.OnClickListener {

	ArrayList<Farm> farms;
	ArrayList<String> farmNames;
	ListView listViewFarms;
	Button createFarm, manageSystem;
	Intent i;
	ManageFarm mf;
	ManageCow mc;
	ManageFootbath mfb;
	ManageVaccine mv;
	ManageCow_Vaccine mcv;
	ManageLimb ml;
	ManageHoof mh;
	ManageZone mz;
	ManageInjury mi;
	ManageZone_Injury mzi;
	ManageLocomotionScore mls;
	ManageMedicine mm;

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	private CharSequence[] options = { "Vacunas",
			"Medicinas",
			"Lesiones"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity_view_farms);
		initialize();
	}

	public void initialize() {
		mf = new ManageFarm(this);
		mc = new ManageCow(this);
		mfb = new ManageFootbath(this);
		mv = new ManageVaccine(this);
		mcv = new ManageCow_Vaccine(this);
		ml = new ManageLimb(this);
		mh = new ManageHoof(this);
		mz = new ManageZone(this);
		mi = new ManageInjury(this);
		mzi = new ManageZone_Injury(this);
		mls = new ManageLocomotionScore(this);
		mm = new ManageMedicine(this);

		databaseInserts();
		createFarm = (Button) findViewById(R.id.bCreateFarm_MainView);
		manageSystem = (Button) findViewById(R.id.bManageSystem_MainView);
		listViewFarms = (ListView) findViewById(R.id.lvFarms);

		farms = new ArrayList<Farm>();
		farmNames = new ArrayList<String>();

		farms = mf.readAllFarm();

		for (Farm farm : farms) {
			farmNames.add(farm.getName());
		}

		listViewFarms.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_2, android.R.id.text1,
				farmNames));
		
		createFarm.setOnClickListener(this);
		manageSystem.setOnClickListener(this);

		listViewFarms.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ArrayList<Farm> farms1 = new ArrayList<Farm>();
				farms1 = mf.readAllFarm();
				int id_farm = farms1.get(position).getId();
				Intent farmDetails = new Intent(MainActivityViewFarms.this,
						FarmDetails.class);
				farmDetails.putExtra("FARM_ID", id_farm);
				startActivity(farmDetails);
			}
		});
	}

	private void databaseInserts() {
		// DATOS DE PRUEBA

//		mf.createFarm("Rosales", "Nicol�s Regules #61, Colonia Centro",
//				"Donardo");
//		mf.createFarm("Terranova",
//				"Calle Pedro Loza #360 esq. Angulo, Zona Centro", "Demetrio");
//		mf.createFarm("Los Sauces",
//				"Carretera Guadalajara - Tala � Etzatl�n Km. 58", "Selena");
//		mf.createFarm("Rio Grande", "Fco. I. Madero #84 esq. Agust�n Y�nez",
//				"Franchesca");
//
//		mfb.createFootbath("PediluvioA", 60, 200, 4, 1);
//		mfb.createFootbath("PediluvioB", 70, 214, 5, 1);
//		mfb.createFootbath("PediluvioC", 80, 190, 4.5, 2);
//		mfb.createFootbath("PediluvioD", 90, 150, 4.2, 2);
//		mfb.createFootbath("PediluvioE", 100, 180, 3.5, 3);
//		mfb.createFootbath("PediluvioF", 60, 175, 5.5, 3);
//		mfb.createFootbath("PediluvioG", 70, 165, 4, 4);
//
//		mc.createCow(111111, "Pecas", "Blanco Ojinegro", "1/1/1992",
//				"TattooDesc", "Disminuci�n en la producci�n de leche",
//				"Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 1);
//		mc.createCow(111111, "Lulu", "Caquete�o", "1/1/1993", "TattooDesc",
//				"Dolor", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 1);
//		mc.createCow(111111, "Negra", "Chino Santandereano", "1/1/1994",
//				"TattooDesc", "Anorexia", "Ninguno", "Ninguno", "Ninguno", 0,
//				0, 0, 1);
//		mc.createCow(111111, "Alegr�a", "Coste�o con Cuernos", "1/1/1995",
//				"TattooDesc", "Dec�bito permanente", "Ninguno", "Ninguno",
//				"Ninguno", 0, 0, 0, 2);
//		mc.createCow(111111, "Duquesa", "Harton del valle", "1/1/1996",
//				"TattooDesc", "Disminuci�n en la producci�n de leche",
//				"Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 2);
//		mc.createCow(111111, "Paquita", "Lucerna", "1/1/1997", "TattooDesc",
//				"Dolor", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 2);
//		mc.createCow(111111, "Lola", "Romosinuano", "1/1/1998", "TattooDesc",
//				"Anorexia", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 3);
//		mc.createCow(111111, "Clarabella", "Sanmartinero", "1/1/1999",
//				"TattooDesc", "Dec�bito permanente", "Ninguno", "Ninguno",
//				"Ninguno", 0, 0, 0, 3);
//		mc.createCow(111111, "Casta�a", "Velazquez", "1/1/1991", "TattooDesc",
//				"Disminuci�n en la producci�n de leche", "Ninguno", "Ninguno",
//				"Ninguno", 0, 0, 0, 3);
//		mc.createCow(111111, "Margarita", "Blanco Ojinegro", "1/1/1990",
//				"TattooDesc", "Dolor", "Ninguno", "Ninguno", "Ninguno", 0, 0,
//				0, 4);
//		mc.createCow(111111, "Reina", "Caquete�o", "1/1/1991", "TattooDesc",
//				"Anorexia", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 4);
//		mc.createCow(111111, "Perla", "Lucerna", "1/1/1995", "TattooDesc",
//				"Dec�bito permanente", "Ninguno", "Ninguno", "Ninguno", 0, 0,
//				0, 4);

			mv.createVaccine(1, "FAF (Fiebre Aftosa)");
			mv.createVaccine(2, "BRU (Brucella)");
			mv.createVaccine(3, "TRI (Triple)");
			mv.createVaccine(4, "CAS (Carb�n Sintom�tico)");
			mv.createVaccine(5, "CRB (Complejo Respiratorio & Reproductivo Bovino)");
			mv.createVaccine(6, "EST (Estomatitis)");
			mv.createVaccine(7, "LEP (Leptospirosis)");
			mv.createVaccine(8, "CLO (Clostridium)");
			mv.createVaccine(9, "RAB(Rabia)");

			ml.createLimb(1, "Miembro anterior izquierdo");
			ml.createLimb(2, "Miembro anterior derecho");
			ml.createLimb(3, "Miembro posterior izquierdo");
			ml.createLimb(4, "Miembro posterior derecho");

			mi.createInjury(1, "(C) Pezu�a de Tirabuz�n",0);
			mi.createInjury(2, "(D) Dermatitis Digital",1);
			mi.createInjury(3, "(E) Erosi�n del Tal�n",1);
			mi.createInjury(4, "(F) Gabarro o Flem�n",1);
			mi.createInjury(5, "(G) Fisura o Grieta Horizontal",0);
			mi.createInjury(6, "(H) Hemorragia de la Suela",0);
			mi.createInjury(7, "(I) Dermatitis Interdigital",1);
			mi.createInjury(8, "(K) Hiperplasia Interdigital",0);
			mi.createInjury(9, "(T) �lcera de Punta",0);
			mi.createInjury(10, "(U) �lcera de la Suela",0);
			mi.createInjury(11, "(V) Fisura o Grieta Vertical",0);
			mi.createInjury(12, "(W) Lesi�n de la L�nea Blanca",0);
			mi.createInjury(13, "(X) Fisura Axial",0);
			mi.createInjury(14, "(Z) Suela Delgada",0);
			
			mm.createMedicine(1, "Formol",3.0,"cc");
			mm.createMedicine(2, "CuSO4", 2.0, "gr");
			mm.createMedicine(3, "Hipoclorito de Sodio", 1.0,"cc");
			mm.createMedicine(4, "OTC", 5.0,"gr");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main_activity_view_farms,
				menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bCreateFarm_MainView:
			i = new Intent(MainActivityViewFarms.this,CreateFarm.class);
			startActivity(i);
			break;
		case R.id.bManageSystem_MainView:

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Selecciona lo que deseas administrar");
			builder.setItems(options, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					switch (item){
					case 0:
						i = new Intent(MainActivityViewFarms.this,ViewVaccines.class);
						startActivity(i);
						break;
					case 1:
						i = new Intent(MainActivityViewFarms.this,ViewMedicines.class);
						startActivity(i);
						break;
					case 2:
						i = new Intent(MainActivityViewFarms.this,ViewInjuries.class);
						startActivity(i);
						break;
					}
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
			break;
		}
	}
}