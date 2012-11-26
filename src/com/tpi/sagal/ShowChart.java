package com.tpi.sagal;

import java.io.File;
import java.io.FileOutputStream;

import org.achartengine.GraphicalView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tpi.sagal.control.ManageFarm;
import com.tpi.sagal.graphs.InjuryGraph;
import com.tpi.sagal.graphs.LocomotionScoringGraph;

public class ShowChart extends Activity implements View.OnClickListener{

	ImageButton backButton;
	TextView farmName;
	LinearLayout chart;
	Button export;
	int farmId, index = 0;
	String chartType;
	ManageFarm mf;
	Intent i;
	GraphicalView gv;
	Bitmap bitmap;
	
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
			bitmap = gv.toBitmap();
			//Does the device have SD)
			String status = Environment.getExternalStorageState();
		    if (status.equals(Environment.MEDIA_MOUNTED)){
		    	File path = Environment.getExternalStorageDirectory();
		    	String absolutePath = path.getAbsolutePath();
		    	try {
		    	          FileOutputStream out = new FileOutputStream(absolutePath);
		    	          bitmap.compress(CompressFormat.PNG, 100, out);
		    	          out.flush();
		    	          out.close();
		    	      } catch(Exception e) {
		    	    	  Toast.makeText(ShowChart.this,
		  						"La gráfica no se pudo exportar.", Toast.LENGTH_SHORT).show();
		    	      }

		    }/*
			try{
				File file = new File(Environment.getExternalStorageDirectory(), "test" + index++ + ".png");
				FileOutputStream output = new FileOutputStream(file);
				bitmap.compress(CompressFormat.PNG, 100, output);
			}catch(Exception e){
				Toast.makeText(ShowChart.this,
						"La gráfica no se pudo exportar.", Toast.LENGTH_SHORT).show();
			}*/
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
			chartType = extras.getString("CHART_TYPE");
			farmId = extras.getInt("FARM_ID");
		}
		
		mf = new ManageFarm(this);
		farmName.setText(mf.searchFarm(farmId).getName());
		
		backButton.setOnClickListener(this);
		export.setOnClickListener(this);
		Log.v("BLAH", "charType: "+chartType);
		// CharType = 1 for LocomotionScoring bar chart
		if (chartType.equals("Puntaje de locomoción")){
			Log.v("BLAH", "hola");
			Log.v("BLAH", "farmId: "+farmId);
			LocomotionScoringGraph lsg = new LocomotionScoringGraph();
			//gv = lsg.getView(this, farmId);
			gv = lsg.getLocomotionScoringBarChartByCow(this, 1);
			chart.addView(gv);
		} else if(chartType.equals("Lesiones encontradas")){
			InjuryGraph ig = new InjuryGraph();
			gv = ig.getView(this, farmId);
			chart.addView(gv);
		}
		
	}
	

}
