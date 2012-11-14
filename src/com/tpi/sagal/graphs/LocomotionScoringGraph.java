package com.tpi.sagal.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class LocomotionScoringGraph {

	public Intent getIntent (Context context){
		
		int[] y = {8, 5, 4, 3, 1};
		
		//Converting data into a Series Object
		CategorySeries series = new CategorySeries("Puntaje de locomoción [1-5]");
		for (int i = 0; i < y.length; i++)
			series.add("Bar " + (i+1), y[i]);
		
		// Object that hold a collection of Series
		// Series could be any type: CategorySeries or TimeSeries
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		

		// Renders a specific serie. One series has one render
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setDisplayChartValues(true);
		renderer.setChartValuesSpacing((float)0.5);
		renderer.setColor(Color.BLUE);
		
		// Renders all the series under a specific customization 
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();		
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.setChartTitle("Frecuencias de los puntajes de locomoción");
		mRenderer.setYTitle("Ejemplares");
		
		Intent intent = ChartFactory.getBarChartIntent(context, dataset, mRenderer, Type.DEFAULT);
		return intent;
	}
	
}