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

import com.tpi.sagal.control.ManageFarm;
import com.tpi.sagal.control.ManageFootbath;
import com.tpi.sagal.entity.Farm;
import com.tpi.sagal.entity.Footbath;

public class ViewFootbaths extends Activity implements View.OnClickListener{

	ListView listViewFootbaths;
	Button createFootbath, editFootbath;
	ArrayList<Footbath> footbaths = new ArrayList<Footbath>();
	ManageFootbath mft;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_footbaths);
		initialize();
	}
	
	private void initialize(){
		createFootbath = (Button) findViewById(R.id.bCreateFootbath);
		editFootbath = (Button) findViewById(R.id.bEditFootbath);
		listViewFootbaths = (ListView) findViewById(R.id.lvFoothbath);
		
		ArrayList<String> fbNames = new ArrayList<String>();
		mft = new ManageFootbath(this);
		footbaths = mft.readAllFootbath();
		
		for(Footbath f : footbaths){
			fbNames.add(f.getName());
		}

		listViewFootbaths.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_2,
						android.R.id.text1, fbNames));
		createFootbath.setOnClickListener(this);

		listViewFootbaths.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				footbaths = mft.readAllFootbath();
				int id_footbath = footbaths.get(position).getId();
				Intent footbathDetails = new Intent("com.tpi.sagal.FOOTBATHDETAILS"); //No existe aun
				footbathDetails.putExtra("FOOTBATH_NAME",id_footbath);
				startActivity(footbathDetails);
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bCreateFootbath:
			Intent i = new Intent("com.tpi.sagal.CREATEFOOTBATH"); //No existe aun
			startActivity(i);
			break;
		case R.id.bEditFootbath:
			
			break;
		}
	}
	
	

}
