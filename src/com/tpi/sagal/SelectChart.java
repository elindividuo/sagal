package com.tpi.sagal;

import java.util.ArrayList;

import org.achartengine.GraphicalView;

import com.tpi.sagal.control.ManageCow;
import com.tpi.sagal.control.ManageFarm;
import com.tpi.sagal.entity.Cow;
import com.tpi.sagal.entity.Farm;
import com.tpi.sagal.graphs.LocomotionScoringGraph;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
	ManageCow mc;
	int farmId, ct, selectedCowId;
	boolean diditwork;
	String chartTypeForPloting;
	
	ArrayList<Cow> cows; ArrayList<String> cowNames;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_chart);
		initialize();
	}

	@Override
	public void onClick(View v) {
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
			
			// Tipo de grafico que se mostrará: 
			// Puntaje de locomoción del hato = @string/radioLocomotionScoringByFarm
			// Lesiones encontradas en el hato = @string/radioInjuries
			// Puntaje de locomoción de un ejemplar = @string/radioLocomotionScoringByCow
			Log.v("BLAH", "Char: "+chartType);
			if (chartType.equals("Puntaje de locomoción del hato")){
				chartTypeForPloting = "Puntaje de locomoción del hato";
				i = new Intent(SelectChart.this, ShowChart.class);
				i.putExtra("CHART_TYPE", chartTypeForPloting);
				i.putExtra("FARM_ID", farmId);
				startActivity(i);
			}
			else if(chartType.equals("Lesiones encontradas en el hato")){
				chartTypeForPloting = "Lesiones encontradas en el hato";
				i = new Intent(SelectChart.this, ShowChart.class);
				i.putExtra("CHART_TYPE", chartTypeForPloting);
				i.putExtra("FARM_ID", farmId);
				startActivity(i);
			}
			else if (chartType.equals("Puntaje de locomoción de un ejemplar")){
				chartTypeForPloting = "Puntaje de locomoción de un ejemplar";
				////////////////////////////////////////////////////////////////////////////////////////
				cows.clear(); cowNames.clear();
				cows = mc.readAllCowFromFarm(farmId);
				
				for (Cow f : cows)
					cowNames.add(f.getName());
				
				final CharSequence[] items = cowNames.toArray(new CharSequence[cowNames.size()]);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Selecciona un ejemplar");
				builder.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						selectedCowId = cows.get(item).getId();
						i = new Intent(SelectChart.this, ShowChart.class);
						i.putExtra("CHART_TYPE", chartTypeForPloting);
						i.putExtra("FARM_ID", farmId);
						i.putExtra("COW_ID", selectedCowId);
						startActivity(i);
					}
				});
				/*
				builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
				*/
				AlertDialog alert = builder.create();
				alert.show();
				Log.v("BLAA COW ID", selectedCowId+"");
			}
			
			break;
		}
	}
	
	public void initialize(){
		backButton = (ImageButton)findViewById(R.id.ibBack_SelectChart);
		plot = (Button)findViewById(R.id.bPlot);
		farmName = (TextView)findViewById(R.id.tvSelectChartFarmName);
		radioChartSelectGroup = (RadioGroup)findViewById(R.id.radioChart);
		
		cows = new ArrayList<Cow>();
		cowNames = new ArrayList<String>();
		
		Bundle extras = getIntent().getExtras();
		if (extras != null){
			farmId = extras.getInt("FARM_ID");
		}
		
		mc = new ManageCow(this);
		
		mf = new ManageFarm(this);
		farmName.setText(mf.searchFarm(farmId).getName());
		
		diditwork = true;
		backButton.setOnClickListener(this);
		plot.setOnClickListener(this);
	}

}
