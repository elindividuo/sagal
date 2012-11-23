package com.tpi.sagal.control;

import android.content.Context;
import android.database.Cursor;

import com.tpi.sagal.dao.ZoneDaoAdapter;

public class ManageZone {
	
private ZoneDaoAdapter zoneDao;
	
	public ManageZone (Context context){
		zoneDao = new ZoneDaoAdapter(context);
		zoneDao.open();
		zoneDao.close();
	}

	public int idZone(int hoofId) {
		zoneDao.open();
		Cursor cursor = zoneDao.readZoneFromHoof(hoofId);
		if(cursor.moveToFirst()){
	        int zoneId = cursor.getInt(cursor.getColumnIndex("_id"));
	        cursor.close();
	        zoneDao.close();
	        return zoneId;
		}
		return 0;	
	}

	public int idZone(int hoofId, int selectedZone) {
		zoneDao.open();
		Cursor cursor = zoneDao.readZoneFromHoof(hoofId,selectedZone);
		if(cursor.moveToFirst()){
	        int zoneId = cursor.getInt(cursor.getColumnIndex("_id"));
	        cursor.close();
	        zoneDao.close();
	        return zoneId;
		}
		return 0;	
	}
}
