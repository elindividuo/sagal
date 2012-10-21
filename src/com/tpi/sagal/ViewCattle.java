package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ViewCattle extends Activity{

	Button createCow;
	ListView listViewCows;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_cattle);
		
		createCow=(Button)findViewById(R.id.bCreateCow_CreateCowView);
		listViewCows=(ListView)findViewById(R.id.lvCattle);
		String[] values = new String[] { "Paquita", "Pecas", "Lulú" };
		listViewCows.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, android.R.id.text1,values));
		listViewCows.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent cattleDetails = new Intent("com.tpi.sagal.CATTLEDETAILS");
				startActivity(cattleDetails);
			}
			
		});
		
		createCow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createCow(v);
			}
		});
	}
	
	public void createCow(View view){
		Intent intent = new Intent("com.tpi.sagal.CREATECOW");
		startActivity(intent);
	}

}
