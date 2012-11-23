package com.tpi.sagal;

import org.achartengine.GraphicalView;

import com.tpi.sagal.control.ManageFarm;
import com.tpi.sagal.entity.Farm;
import com.tpi.sagal.graphs.LocomotionScoringGraph;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SelectChart extends Activity implements View.OnClickListener{

	ImageButton backButton;
	Button plot;
	TextView farmName;
	RadioGroup radioChartSelectGroup;
	RadioButton radioChartButton;
	
	Intent i;
	ManageFarm mf;
	int farmId, ct;
	boolean diditwork;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_chart);
		initialize();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.ibBack_SelectChart:
			i = new Intent(SelectChart.this, FarmDetails.class);
			i.putExtra("FARM_ID", farmId);
			startActivity(i);
			break;
		case R.id.bPlot:
			//LocomotionScoringGraph lsg = new LocomotionScoringGraph();
			//GraphicalView gv = lsg.getView(this, farmId);
			int selectedId = radioChartSelectGroup.getCheckedRadioButtonId(); // get selected radio button from radioGroup
			radioChartButton = (RadioButton)findViewById(selectedId); // find the radioButton by returned id
			String chartType = radioChartButton.getText().toString();
			
			// Tipo de grafico que se mostrará: 1 para puntaje de locomocion y 2 para tipo de enfermedades
			Log.v("BLAH", "Char: "+chartType);
			if (chartType.equals("Puntaje de locomoción")){
				ct = 1;
				i = new Intent(SelectChart.this, ShowChart.class);
				i.putExtra("CHART_TYPE", ct);
				i.putExtra("FARM_ID", farmId);
				startActivity(i);
			}
			else if(chartType.equals("Lesiones encontradas")){
				//Put code for plot injuries percentaje
				ct = 2;
				i = new Intent(SelectChart.this, ShowChart.class);
				i.putExtra("CHART_TYPE", ct);
				i.putExtra("FARM_ID", farmId);
				startActivity(i);
			}
			break;
		}
	}
	
	public void initialize(){
		backButton = (ImageButton)findViewById(R.id.ibBack_SelectChart);
		plot = (Button)findViewById(R.id.bPlot);
		farmName = (TextView)findViewById(R.id.tvSelectChartFarmName);
		radioChartSelectGroup = (RadioGroup)findViewById(R.id.radioChart);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null){
			farmId = extras.getInt("FARM_ID");
		}
		
		mf = new ManageFarm(this);
		farmName.setText(mf.searchFarm(farmId).getName());
		
		diditwork = true;
		backButton.setOnClickListener(this);
		plot.setOnClickListener(this);
	}

}
