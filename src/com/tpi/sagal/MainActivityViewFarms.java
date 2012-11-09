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
import android.widget.Toast;

import com.tpi.sagal.control.ManageFarm;
import com.tpi.sagal.entity.Farm;

public class MainActivityViewFarms extends Activity implements View.OnClickListener {

	ListView listViewFarms;
	Button createFarm;
	Intent i;
	ManageFarm mf;
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity_view_farms);
		initialize();
	}

	public void initialize() {
		createFarm = (Button) findViewById(R.id.bCreateFarm_MainView);
		listViewFarms = (ListView) findViewById(R.id.lvFarms);
		ArrayList<Farm> farms = new ArrayList<Farm>();
		ArrayList<String> farmNames = new ArrayList<String>();
		mf = new ManageFarm(this);
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
				Intent farmDetails = new Intent("com.tpi.sagal.FARMDETAILS");
				farmDetails.putExtra("FARM_ID",id_farm);
				startActivity(farmDetails);
			}
		});
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