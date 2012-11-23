package com.tpi.sagal.control;

import android.content.Context;
import android.database.Cursor;

import com.tpi.sagal.dao.LimbDaoAdapter;
import com.tpi.sagal.entity.Cow;
import com.tpi.sagal.entity.Farm;

public class ManageLimb {

	private LimbDaoAdapter limbDao;
	
	public ManageLimb (Context context){
		limbDao = new LimbDaoAdapter(context);
		limbDao.open();
		limbDao.close();
	}
	
	public void createLimb (int idLimb, String nombre) {
		limbDao.open();
		limbDao.createLimb(idLimb, nombre);
		limbDao.close();
	}
	
	public String readLimb(int idLimb){
		limbDao.open();
		Cursor cursor = limbDao.readLimb(idLimb); 
		if(cursor.moveToFirst()){
	        String name = cursor.getString(cursor.getColumnIndex("limb_name"));
	        cursor.close();
	        limbDao.close();
	        return name;
		}
		return null;
	}
	
}
