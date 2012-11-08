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

public class ViewCattle extends Activity implements View.OnClickListener{

	Button createCow;
	ListView listViewCows;
	Intent i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_cattle);
		initialize();
		
		listViewCows.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent cattleDetails = new Intent("com.tpi.sagal.CATTLEDETAILS");
				startActivity(cattleDetails);
			}
			
		});
	}
	
	public void initialize(){
		createCow=(Button)findViewById(R.id.bCreateCow_CreateCowView);
		listViewCows=(ListView)findViewById(R.id.lvCattle);
		String[] values = new String[] { "Paquita", "Pecas", "Lulú" };
		listViewCows.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, android.R.id.text1,values));
		
		createCow.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.bCreateCow_CreateCowView:
			i = new Intent("com.tpi.sagal.CREATECOW");
			startActivity(i);
			break;
		}	
	}	
}