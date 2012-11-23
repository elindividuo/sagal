package com.tpi.sagal.control;

import java.util.ArrayList;

import com.tpi.sagal.dao.InjuryDaoAdapter;
import com.tpi.sagal.entity.Cow;
import com.tpi.sagal.entity.Farm;
import com.tpi.sagal.entity.Injury;

import android.content.Context;
import android.database.Cursor;

public class ManageInjury {
	private InjuryDaoAdapter injuryDao;
	
	public ManageInjury (Context context){
		injuryDao = new InjuryDaoAdapter (context);
		injuryDao.open();
		injuryDao.close();
	}
	
	public void createInjury(int id, String name, String abbreviation){
		injuryDao.open();
		injuryDao.createInjury(id, name, abbreviation);
		injuryDao.close();
	}
	
	public ArrayList<Injury> readAllInjuries(){
		injuryDao.open();
		Cursor cursor = injuryDao.readInjury();
		ArrayList<Injury> injuries = new ArrayList<Injury>();
		if (cursor.moveToFirst()){
			do
			{
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				String name = cursor.getString(cursor.getColumnIndex("injury_name"));
				String abb = cursor.getString(cursor.getColumnIndex("injury_abbreviation"));
				injuries.add(new Injury(id, name, abb));
			} while ( cursor.moveToNext());
		}
		cursor.close();
		injuryDao.close();
		return injuries;
	}
	
}
