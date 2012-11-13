package com.tpi.sagal.control;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import com.tpi.sagal.dao.FootbathDaoAdapter;
import com.tpi.sagal.entity.Footbath;

public class ManageFootbath {
private FootbathDaoAdapter FootbathDao;
	
	public ManageFootbath(Context context)
	{
		FootbathDao = new FootbathDaoAdapter(context);
	}
	
	public void createFootbath(String name, double width, double deep, double height, String medicine, float percentage){
		FootbathDao.open();
		FootbathDao.createFootbath(name, width, deep, height, medicine, percentage);
		FootbathDao.close();
	}
	
	public ArrayList<Footbath> readAllFootbath(){
		FootbathDao.open();
		Cursor cursor = FootbathDao.readFootbath();
		ArrayList<Footbath> Footbaths = new ArrayList<Footbath>();
		if (cursor.moveToFirst()){
			do
			{
			   int id = cursor.getInt(cursor.getColumnIndex("_id"));
               String name = cursor.getString(cursor.getColumnIndex("Footbath_name"));
               double width = cursor.getDouble(cursor.getColumnIndex("Footbath_width"));
               double deep = cursor.getDouble(cursor.getColumnIndex("Footbath_deep"));
               double height = cursor.getDouble(cursor.getColumnIndex("Footbath_height"));
               String medicine = cursor.getString(cursor.getColumnIndex("Footbath_medicine_type"));
               float percentage = cursor.getFloat(cursor.getColumnIndex("Footbath_percentage"));
              
               Footbaths.add( new Footbath(id, name, width, deep, height, medicine, percentage));             
			} while ( cursor.moveToNext());

       }
       cursor.close();
       FootbathDao.close();
       return Footbaths;
	}
	
	public Footbath searchFootbath(int id){
		FootbathDao.open();
		Cursor cursor = FootbathDao.searchFootbath(id);
		if(cursor.moveToFirst()){
			int fid = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("Footbath_name"));
            double width = cursor.getDouble(cursor.getColumnIndex("Footbath_width"));
            double deep = cursor.getDouble(cursor.getColumnIndex("Footbath_deep"));
            double height = cursor.getDouble(cursor.getColumnIndex("Footbath_height"));
            String medicine = cursor.getString(cursor.getColumnIndex("Footbath_medicine_type"));
            float percentage = cursor.getFloat(cursor.getColumnIndex("Footbath_percentage"));
	        cursor.close();
	        FootbathDao.close();
	        return new Footbath(id, name, width, deep, height, medicine, percentage);
		}
		return null;
	}
	
	public void deleteFootbath(int id){
		FootbathDao.open();
		FootbathDao.deleteFootbath(id);
		FootbathDao.close();
	}
	
	public void updateFootbath(int id, String name, double width, double deep, double height, String medicine, float percentage){
		FootbathDao.open();
		FootbathDao.updateFootbath(id, name, width, deep, height, medicine, percentage);
		FootbathDao.close();
	}
}
