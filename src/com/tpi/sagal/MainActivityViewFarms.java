package com.tpi.sagal;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.tpi.sagal.control.ManageCow;
import com.tpi.sagal.control.ManageCow_Vaccine;
import com.tpi.sagal.control.ManageFarm;
import com.tpi.sagal.control.ManageFootbath;
import com.tpi.sagal.control.ManageHoof;
import com.tpi.sagal.control.ManageInjury;
import com.tpi.sagal.control.ManageLimb;
import com.tpi.sagal.control.ManageVaccine;
import com.tpi.sagal.control.ManageZone;
import com.tpi.sagal.control.ManageZone_Injury;
import com.tpi.sagal.entity.Farm;

public class MainActivityViewFarms extends Activity implements View.OnClickListener {

	ArrayList<Farm> farms;
	ArrayList<String> farmNames;
	ListView listViewFarms;
	Button createFarm;
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
	
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

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
		
		//databaseInserts();
		createFarm = (Button) findViewById(R.id.bCreateFarm_MainView);
		listViewFarms = (ListView) findViewById(R.id.lvFarms);
		
		farms = new ArrayList<Farm>();
		farmNames = new ArrayList<String>();
		
		
		farms = mf.readAllFarm();
		
		for(Farm farm:farms){
			farmNames.add(farm.getName());
		}

		listViewFarms.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_2,
						android.R.id.text1, farmNames));
		createFarm.setOnClickListener(this);

		listViewFarms.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ArrayList<Farm> farms1 = new ArrayList<Farm>();
				farms1 = mf.readAllFarm();
				int id_farm=farms1.get(position).getId();
				Intent farmDetails = new Intent(MainActivityViewFarms.this,FarmDetails.class);
				farmDetails.putExtra("FARM_ID",id_farm);
				startActivity(farmDetails);
			}
		});
	}
	
	private void databaseInserts() {
		mf.createFarm("Rosales", "Nicolás Regules #61, Colonia Centro", "Donardo");
		mf.createFarm("Terranova", "Calle Pedro Loza #360 esq. Angulo, Zona Centro", "Demetrio");
		mf.createFarm("Los Sauces", "Carretera Guadalajara - Tala – Etzatlán Km. 58", "Selena");
		mf.createFarm("Rio Grande", "Fco. I. Madero #84 esq. Agustín Yánez", "Franchesca");
		
		mfb.createFootbath("PediluvioA", 60, 200, 4, 1);
		mfb.createFootbath("PediluvioB", 70, 214, 5, 1);
		mfb.createFootbath("PediluvioC", 80, 190, 4.5, 2);
		mfb.createFootbath("PediluvioD", 90, 150, 4.2, 2);
		mfb.createFootbath("PediluvioE", 100, 180, 3.5, 3);
		mfb.createFootbath("PediluvioF", 60, 175, 5.5, 3);
		mfb.createFootbath("PediluvioG", 70, 165, 4, 4);
		
		mc.createCow(111111, "Pecas", "Blanco Ojinegro", "1/1/1992", "TattooDesc", "Disminución en la producción de leche", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 1);
		mc.createCow(111111, "Lulu", "Caqueteño", "1/1/1993", "TattooDesc", "Dolor", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 1);
		mc.createCow(111111, "Negra", "Chino Santandereano", "1/1/1994", "TattooDesc", "Anorexia", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 1);
		mc.createCow(111111, "Alegría", "Costeño con Cuernos", "1/1/1995", "TattooDesc", "Decúbito permanente", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 2);
		mc.createCow(111111, "Duquesa", "Harton del valle", "1/1/1996", "TattooDesc", "Disminución en la producción de leche", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 2);
		mc.createCow(111111, "Paquita", "Lucerna", "1/1/1997", "TattooDesc", "Dolor", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 2);
		mc.createCow(111111, "Lola", "Romosinuano", "1/1/1998", "TattooDesc", "Anorexia", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 3);
		mc.createCow(111111, "Clarabella", "Sanmartinero", "1/1/1999", "TattooDesc", "Decúbito permanente", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 3);
		mc.createCow(111111, "Castaña", "Velazquez", "1/1/1991", "TattooDesc", "Disminución en la producción de leche", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 3);
		mc.createCow(111111, "Margarita", "Blanco Ojinegro", "1/1/1990", "TattooDesc", "Dolor", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 4);
		mc.createCow(111111, "Reina", "Caqueteño", "1/1/1991", "TattooDesc", "Anorexia", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 4);
		mc.createCow(111111, "Perla", "Lucerna", "1/1/1995", "TattooDesc", "Decúbito permanente", "Ninguno", "Ninguno", "Ninguno", 0, 0, 0, 4);
		
		mv.createVaccine("FAF (Fiebre Aftosa)");
		mv.createVaccine("BRU (Brucella)");
		mv.createVaccine("TRI (Triple)");
		mv.createVaccine("CAS (Carbón Sintomático)");
		mv.createVaccine("CRB (Complejo Respiratorio & Reproductivo Bovino)");
		mv.createVaccine("EST (Estomatitis)");
		mv.createVaccine("LEP (Leptospirosis)");
		mv.createVaccine("CLO (Clostridium)");
		mv.createVaccine("RAB(Rabia)");
		
		mi.createInjury( "Pezuña de Tirabuzón", "(C)" );
		mi.createInjury("Dermatitis Digital" , "(D)");
		mi.createInjury( "Erosión del Talón","(E)" );
		mi.createInjury( "Gabarro o Flemón", "(F)");
		mi.createInjury( "Fisura o Grieta Horizontal", "(G)");
		mi.createInjury("Hemorragia de la Suela" , "(H)");
		mi.createInjury("Dermatitis Interdigital" ,"(I)" );
		mi.createInjury( "Hiperplasia Interdigital","(K)" );
		mi.createInjury( "Úlcera de Punta", "(T)");
		mi.createInjury( "Úlcera de la Suela", "(U)");
		mi.createInjury("Fisura o Grieta Vertical" , "(V)");
		mi.createInjury("Lesión de la Línea Blanca" ,"(W)" );
		mi.createInjury("Fisura Axial" , "(X)");
		mi.createInjury("Suela Delgada" , "(Z)");
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
			i = new Intent("com.tpi.sagal.CREATEFARM");
			startActivity(i);
			break;
		}
	}
}