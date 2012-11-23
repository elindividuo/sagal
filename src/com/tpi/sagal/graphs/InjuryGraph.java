package com.tpi.sagal.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

public class InjuryGraph extends Activity{

	public GraphicalView getView (Context context, int id_farm){
		
		int values[] = {1,5,7,8,2};
		CategorySeries series = new CategorySeries("Enfermedades");
		
		int k = 0;
		for (int value:values){
			series.add("Section " + ++k, value);
		}
		
		// Number of values = number of colors
		int[] colors = new int[]{Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN};
		
	   DefaultRenderer renderer = new DefaultRenderer();
	   for (int color:colors){
		   SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		   r.setColor(color);
		   renderer.addSeriesRenderer(r);
	   }
		
	   return ChartFactory.getPieChartView(context, series, renderer);
	   
	}
}
