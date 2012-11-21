package com.tpi.sagal;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.tpi.sagal.control.ManageCow;
import com.tpi.sagal.entity.Cow;

public class ViewCattle extends Activity implements View.OnClickListener{

	Button createCow;
	ListView listViewCows;
	Intent i;
	int farmId;
	ArrayList<Cow> cows;
	ArrayList<String> cowNames;
	ManageCow mc;
	ImageButton backButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_cattle);
		initialize();
	}
	
	public void initialize(){
		
		backButton = (ImageButton) findViewById(R.id.ibBack_CattleView);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			farmId = extras.getInt("FARM_ID");
		}
		
		cowNames = new ArrayList<String>();
		cows = new ArrayList<Cow>();
		
		mc = new ManageCow(this);
		cows = mc.readAllCowFromFarm(farmId);
		
		for(Cow f : cows){
			cowNames.add(f.getName());
		}
		
		createCow=(Button)findViewById(R.id.bCreateCow_CreateCowView);
		listViewCows=(ListView)findViewById(R.id.lvCattle);
		listViewCows.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, android.R.id.text1,cowNames));
		
		listViewCows.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				
				cows=mc.readAllCowFromFarm(farmId);
				int idCow = cows.get(position).getId();
				Intent cattleDetails = new Intent(ViewCattle.this, CattleDetails.class);
				cattleDetails.putExtra("COW_ID", idCow);
				cattleDetails.putExtra("FARM_ID",farmId);
				startActivity(cattleDetails);
			}
			
		});
		
		
		createCow.setOnClickListener(this);
		backButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.bCreateCow_CreateCowView:
			i = new Intent(ViewCattle.this,CreateCow.class);
			i.putExtra("FARM_ID", farmId);
			startActivity(i);
			break;
		
		case R.id.ibBack_CattleView:
			i = new Intent(ViewCattle.this,FarmDetails.class);
			i.putExtra("FARM_ID", farmId);
			startActivity(i);
			break;
		}	
	}	
}