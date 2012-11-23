package com.tpi.sagal;

import org.achartengine.GraphicalView;

import com.tpi.sagal.control.ManageFarm;
import com.tpi.sagal.graphs.LocomotionScoringGraph;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowChart extends Activity implements View.OnClickListener{

	ImageButton backButton;
	TextView farmName;
	LinearLayout chart;
	Button export;
	int chartType, farmId;
	ManageFarm mf;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_chart);
		initialize();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.ibBack_ShowChart:
			i = new Intent(ShowChart.this, SelectChart.class);
			i.putExtra("FARM_ID", farmId);
			startActivity(i);
			break;
		case R.id.bExportChart:
			break;
		}
		
	}
	
	public void initialize(){
		backButton = (ImageButton)findViewById(R.id.ibBack_ShowChart);
		farmName = (TextView)findViewById(R.id.tvShowChartFarmName);
		chart = (LinearLayout)findViewById(R.id.linearLayoutChart);
		export = (Button)findViewById(R.id.bExportChart);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null){
			chartType = extras.getInt("CHART_TYPE");
			farmId = extras.getInt("FARM_ID");
		}
		
		mf = new ManageFarm(this);
		farmName.setText(mf.searchFarm(farmId).getName());
		
		backButton.setOnClickListener(this);
		export.setOnClickListener(this);
		Log.v("BLAH", "charType: "+chartType);
		// CharType = 1 for LocomotionScoring bar chart
		if (chartType == 1){
			Log.v("BLAH", "hola");
			Log.v("BLAH", "farmId: "+farmId);
			LocomotionScoringGraph lsg = new LocomotionScoringGraph();
			GraphicalView gv = lsg.getView(this, farmId);
			chart.addView(gv);
		}
		
	}
	

}
