package com.tpi.sagal.control;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.tpi.sagal.dao.Zone_InjuryDaoAdapter;

public class ManageZone_Injury {

	private Zone_InjuryDaoAdapter zone_injDao;
	
	public ManageZone_Injury (Context context){
		zone_injDao = new Zone_InjuryDaoAdapter(context);
		zone_injDao.open();
		zone_injDao.close();
	}
	
	public void deleteZone_Injury (int zoneId){
		zone_injDao.open();
		zone_injDao.deleteZone_Injury(zoneId);
		zone_injDao.close();
	}

	public void createZone_Injury(int zoneId, Integer injuryId) {
		zone_injDao.open();
		zone_injDao.createZone_Injury(zoneId, injuryId);
		zone_injDao.close();		
	}

	public ArrayList<String> searchZone_Injury(int zoneId) {
		zone_injDao.open();
		ArrayList<String> injuries = new ArrayList<String>();
		Cursor cursor = zone_injDao.searchZone_Injury(zoneId);
		if (cursor.moveToFirst()) {
			do {
				String name = cursor.getString(cursor.getColumnIndex("injury_name"));
				String abb = cursor.getString(cursor.getColumnIndex("injury_abbreviation"));
				String inj = abb + " "+ name;
				injuries.add(inj);
			} while (cursor.moveToNext());

		}
		cursor.close();
		zone_injDao.close();
		return injuries;
	}	
}