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
import android.widget.ListView;
import com.tpi.sagal.control.ManageFootbath;
import com.tpi.sagal.entity.Footbath;

public class ViewFootbaths extends Activity implements View.OnClickListener{

	ListView listViewFootbaths;
	Button createFootbath;
	ArrayList<Footbath> footbaths;
	ArrayList<String> fbNames;
	ManageFootbath mft;
	int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_footbaths);
		initialize();
	}
	
	private void initialize(){
		
		createFootbath = (Button) findViewById(R.id.bCreateFootbath);
		listViewFootbaths = (ListView) findViewById(R.id.lvFoothbath);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			id = extras.getInt("FARM_ID");
		}
		
		fbNames = new ArrayList<String>();
		footbaths = new ArrayList<Footbath>();
		
		mft = new ManageFootbath(this);
		footbaths = mft.readAllFootbathFromFarm(id);
		
		for(Footbath f : footbaths){
			fbNames.add(f.getName());
		}

		listViewFootbaths.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_2,
						android.R.id.text1, fbNames));
		

		listViewFootbaths.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				footbaths = mft.readAllFootbath();
				int id_footbath = footbaths.get(position).getId();
				Intent footbathDetails = new Intent(ViewFootbaths.this,FootbathDetails.class);
				footbathDetails.putExtra("FOOTBATH_ID",id_footbath);
				startActivity(footbathDetails);
			}
		});
		
		
		createFootbath.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bCreateFootbath:
			Intent i = new Intent(ViewFootbaths.this,CreateFootbath.class);
			i.putExtra("FARM_ID", id);
			startActivity(i);
			break;
		}
	}
	
	

}
