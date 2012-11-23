package com.tpi.sagal.control;

import android.content.Context;
import android.database.Cursor;

import com.tpi.sagal.dao.HoofDaoAdapter;

public class ManageHoof {
	private HoofDaoAdapter hoofDao;
	
	public ManageHoof (Context context){
		hoofDao = new HoofDaoAdapter(context);
		hoofDao.open();
		hoofDao.close();
	}
	
	public int idHoof(int cowId, int limbId){
		hoofDao.open();
		Cursor cursor= hoofDao.readHoofFromCowAndLimb(cowId, limbId);
		if(cursor.moveToFirst()){
	        int hoofId = cursor.getInt(cursor.getColumnIndex("_id"));
	        cursor.close();
	        hoofDao.close();
	        return hoofId;
		}
		return 0;	
	}
}
