package com.tpi.sagal.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.tpi.sagal.control.ManageCow;

public class LocomotionScoringGraph extends Activity {

	public GraphicalView getView (Context context, int id_farm){
		Log.v("BLAH", "id_farm en Graph" + id_farm);
		ManageCow mc = new ManageCow(context);
		int[] y = new int [5];
		double[] z = new double [5];		
		for (int i = 0; i<y.length; i++){
			y[i] = 0; z[i] = 0;
		}
			
		y = mc.getLocomotionScoring(id_farm);
		z = mc.getIdealLocomotionScoringFromCattle(id_farm); 
		
		//int[] y = {5,4,7,2,3};
		//Data 1
		//Converting data into a Series Object
		CategorySeries series = new CategorySeries("Puntaje de locomoción [1-5] del hato.");
		for (int i = 0; i < y.length; i++)
			series.add("Bar " + (i+1), y[i]);
		
		//Data 2
		//Converting data into a Series Object
		CategorySeries series2 = new CategorySeries("Puntaje de locomoción [1-5] ideal.");
		for (int i = 0; i < z.length; i++)
			series2.add("Bar " + (i+1), z[i]);

		
		// Object that hold a collection of Series
		// Series could be any type: CategorySeries or TimeSeries
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		dataset.addSeries(series2.toXYSeries());
		

		// Renders a specific serie. One series has one render
		// Customization Serie 1 = Cattle Locomotion Scoring
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setDisplayChartValues(true);
		renderer.setChartValuesSpacing((float)5.5);
		renderer.setColor(Color.BLUE);
		// Customization Serie 2 = Cattle Ideal Locomotion Scoring
		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setDisplayChartValues(true);
		renderer2.setChartValuesSpacing((float)0.5);
		renderer2.setColor(Color.CYAN);
		
		// Renders all the series under a specific customization 
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();		
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(renderer2);
		mRenderer.setChartTitle("Frecuencias de los puntajes de locomoción");
		mRenderer.setXTitle("Puntaje de locomoción");
		mRenderer.setYTitle("Ejemplares");
		mRenderer.setZoomButtonsVisible(true);
		
		/*
		mRenderer.setXLabels(1);
		mRenderer.addXTextLabel(1, "Locomotion 1");
		mRenderer.addXTextLabel(2, "Locomotion 2");
		mRenderer.addXTextLabel(3, "Locomotion 3");
		mRenderer.addXTextLabel(4, "Locomotion 4");
		mRenderer.addXTextLabel(5, "Locomotion 5");
		mRenderer.setShowCustomTextGrid(true);
		*/
		
		return ChartFactory.getBarChartView(context, dataset, mRenderer, Type.DEFAULT);
	}
	
}