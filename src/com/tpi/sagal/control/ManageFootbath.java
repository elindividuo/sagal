package com.tpi.sagal.control;

import java.util.ArrayList;


import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.util.Log;

import com.tpi.sagal.R;
import com.tpi.sagal.dao.FootbathDaoAdapter;
import com.tpi.sagal.entity.Farm;
import com.tpi.sagal.entity.Footbath;

public class ManageFootbath{
private FootbathDaoAdapter footbathDao;
private ManageFarm mf;
	
	public ManageFootbath(Context context)
	{
		footbathDao = new FootbathDaoAdapter(context);
		mf = new ManageFarm(context);
		footbathDao.open();
		footbathDao.close();
}
	
	public void createFootbath(String name, double width, double deep, double height, String medicine, double quantity, int farmId){
		footbathDao.open();
		footbathDao.createFootbath(name, width, deep, height, medicine, quantity, farmId);
		footbathDao.close();
	}
	
	public void createFootbath(String name, double width, double deep, double height, int farmId){
		footbathDao.open();
		footbathDao.createFootbath(name, width, deep, height, farmId);
		footbathDao.close();
	}
	
	
	public ArrayList<Footbath> readAllFootbath(){
		footbathDao.open();
		Cursor cursor = footbathDao.readFootbath();
		ArrayList<Footbath> Footbaths = new ArrayList<Footbath>();
		if (cursor.moveToFirst()){
			do
			{
			   int id = cursor.getInt(cursor.getColumnIndex("_id"));
               String name = cursor.getString(cursor.getColumnIndex("footbath_name"));
               double width = cursor.getDouble(cursor.getColumnIndex("footbath_width"));
               double deep = cursor.getDouble(cursor.getColumnIndex("footbath_deep"));
               double height = cursor.getDouble(cursor.getColumnIndex("footbath_height"));
               String medicine = cursor.getString(cursor.getColumnIndex("footbath_medicine_type"));
               double quantity = cursor.getDouble(cursor.getColumnIndex("footbath_medicine_quantity"));
               int farm_id = cursor.getInt(cursor.getColumnIndex("farm_id"));
               Farm farm = mf.searchFarm(farm_id);
               
               Footbaths.add( new Footbath(id, name, width, deep, height, medicine, quantity, farm));             
			} while ( cursor.moveToNext());

       }
       cursor.close();
       footbathDao.close();
       return Footbaths;
	}
	
	public ArrayList<Footbath> readAllFootbathFromFarm(int id){
		footbathDao.open();
		Cursor cursor = footbathDao.readFootbathFormFarm(id);
		ArrayList<Footbath> Footbaths = new ArrayList<Footbath>();
		if (cursor.moveToFirst()){
			do
			{
			   int fid = cursor.getInt(cursor.getColumnIndex("_id"));
               String name = cursor.getString(cursor.getColumnIndex("footbath_name"));
               double width = cursor.getDouble(cursor.getColumnIndex("footbath_width"));
               double deep = cursor.getDouble(cursor.getColumnIndex("footbath_deep"));
               double height = cursor.getDouble(cursor.getColumnIndex("footbath_height"));
               String medicine = cursor.getString(cursor.getColumnIndex("footbath_medicine_type"));
               double quantity = cursor.getDouble(cursor.getColumnIndex("footbath_medicine_quantity"));
               int farm_id = cursor.getInt(cursor.getColumnIndex("farm_id"));
               Farm farm = mf.searchFarm(farm_id);
               
               Footbaths.add( new Footbath(fid, name, width, deep, height, medicine, quantity, farm));             
			} while ( cursor.moveToNext());

       }
       cursor.close();
       footbathDao.close();
       return Footbaths;
	}
	
	public Footbath searchFootbath(int id){
		footbathDao.open();
		Cursor cursor = footbathDao.searchFootbath(id);
		if(cursor.moveToFirst()){
			int fid = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("footbath_name"));
            double width = cursor.getDouble(cursor.getColumnIndex("footbath_width"));
            double deep = cursor.getDouble(cursor.getColumnIndex("footbath_deep"));
            double height = cursor.getDouble(cursor.getColumnIndex("footbath_height"));
            String medicine = cursor.getString(cursor.getColumnIndex("footbath_medicine_type"));
            double quantity = cursor.getDouble(cursor.getColumnIndex("footbath_medicine_quantity"));
	        int farm_id = cursor.getInt(cursor.getColumnIndex("farm_id"));
            cursor.close();
	        footbathDao.close();
            Farm farm = mf.searchFarm(farm_id);          
	        return new Footbath(fid, name, width, deep, height, medicine, quantity,farm);
		}
		return null;
	}
	
	public void deleteFootbath(int id){
		footbathDao.open();
		footbathDao.deleteFootbath(id);
		footbathDao.close();
	}
	
	public void updateFootbath(int id, String name, double width, double deep, double height, String medicine, double quantity){
		footbathDao.open();
		footbathDao.updateFootbath(id, name, width, deep, height, medicine, quantity);
		footbathDao.close();
	}
	
	public void updateFootbath(int id, String name, double width, double deep, double height){
		footbathDao.open();
		footbathDao.updateFootbath(id, name, width, deep, height);
		footbathDao.close();
	}
	
	public double computeQuantity (double width, double deep, double height, String medicineType){
		// http://www.metric-conversions.org/es/volumen/centimetros-cubicos-a-litros.htm
		
		double concentration, suitableVol;

		// Formol (3%) = @string/radioFormol
		if (medicineType.equals("Formol (3%)")){
			concentration = 3;
			suitableVol = 100;
			return concentration * width * deep * height / suitableVol;
		}
		else {
			//CuSO4 (2%) = @string/radioCuSO4
			if (medicineType.equals("CuSO4 (2%)")){
				concentration = 2;
				suitableVol = 100;
				return concentration * width * deep * height / suitableVol;
			}
			else {
				//Hipoclorito de Sodio (1%) = @string/radioHipocloritoDeSodio
				if (medicineType.equals("Hipoclorito de Sodio (1%)")){
					concentration = 1;
					suitableVol = 100;
					return concentration * width * deep * height / suitableVol;
				}
				else{
					// OTC (5%) = @string/radioOTC
					if (medicineType.equals("OTC (5%)")){
						concentration = 5;
						suitableVol = 1000;
						return concentration * width * deep * height / suitableVol;
					}
					else
						return 0.0;
				}
			}
		}
		
		
	}
}
